package ru.neikendov.embedicacardirectory.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.neikendov.embedicacardirectory.model.Car;
import ru.neikendov.embedicacardirectory.repo.CarRepo;
import ru.neikendov.embedicacardirectory.service.CarService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;

    @Override
    public ResponseEntity<?> getCars(String filter, String dateFrom, String dateTo) {
        List<Car> list = getCars(filter);
        if (!dateFrom.equals("") && isNumber(dateFrom)) {
            list = getCarsDateFrom(list, dateFrom);
        }
        if (!dateTo.equals("")&& isNumber(dateTo)) {
            list = getCarsDateTo(list, dateTo);
        }
        return ResponseEntity.ok().body(list);
    }

    private boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Car> getCarsDateFrom(List<Car> list, String dateFrom) {
        int dateFromInt = Integer.parseInt(dateFrom);
        return list.stream()
                .filter(car -> car.getYearOfIssue() >= dateFromInt)
                .toList();
    }

    private List<Car> getCarsDateTo(List<Car> list, String dateTo) {
        int dateToInt = Integer.parseInt(dateTo);
        return list.stream()
                .filter(car -> car.getYearOfIssue() <= dateToInt)
                .toList();
    }

    @Cacheable(value = "cars")
    public List<Car> getCars(String filter) {
        return switch (filter) {
            case "reg" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "registerSign"));
            case "model" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "carModel"));
            case "color" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "color"));
            case "yearasc" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "yearOfIssue"));
            case "yeardesc" -> carRepo.findAll(Sort.by(Sort.Direction.DESC, "yearOfIssue"));
            default -> carRepo.findAll();
        };
    }

    @Override
    @CachePut(value = "cars")
    public ResponseEntity<?> addCar(Car newCar) {
        try {
            Car oldCar =  carRepo.getCarByRegisterSignAndCarModelAndColorAndYearOfIssue(
                    newCar.getRegisterSign(),
                    newCar.getCarModel(),
                    newCar.getColor(),
                    newCar.getYearOfIssue()
            );
            if (oldCar != null) throw new Exception("car already exists");
            newCar.setCreationTime(LocalDateTime.now());
            return ResponseEntity.ok().body(carRepo.save(newCar));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @Override
    @CacheEvict("cars")
    public ResponseEntity<?> deleteCar(Long id) {
        Car car = carRepo.findCarById(id);
        if (car != null) {
            carRepo.delete(car);
            return ResponseEntity.ok().body("Car with id: " + id + " deleted");
        } else {
            return ResponseEntity.ok().body("Car not found");
        }

    }
}
