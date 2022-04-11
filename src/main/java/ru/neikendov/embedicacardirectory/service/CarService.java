package ru.neikendov.embedicacardirectory.service;

import org.springframework.http.ResponseEntity;
import ru.neikendov.embedicacardirectory.model.Car;

import java.util.List;

public interface CarService {

    ResponseEntity<?> getCars(String filter, String dateFrom, String dateTo);
    ResponseEntity<?> addCar(Car newCar);
    ResponseEntity<?> deleteCar(Long id);

}
