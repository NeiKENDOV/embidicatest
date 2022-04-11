package ru.neikendov.embedicacardirectory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neikendov.embedicacardirectory.model.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Car getCarByRegisterSignAndCarModelAndColorAndYearOfIssue(
            String registerSign,
            String carModel,
            String color,
            Integer yearOfIssue
    );

    Car findCarById(Long id);
}
