package ru.neikendov.embedicacardirectory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neikendov.embedicacardirectory.model.Car;
import ru.neikendov.embedicacardirectory.service.CarService;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<?> getCars(
            @RequestParam(defaultValue = "", required = false) String filter,
            @RequestParam(defaultValue = "", required = false) String dateFrom,
            @RequestParam(defaultValue = "", required = false) String dateTo
    ) {
        return carService.getCars(filter, dateFrom, dateTo);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCar(@RequestParam Long id) {
        return carService.deleteCar(id);
    }
}
