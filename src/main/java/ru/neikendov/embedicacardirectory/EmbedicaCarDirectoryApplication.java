package ru.neikendov.embedicacardirectory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import ru.neikendov.embedicacardirectory.model.Car;
import ru.neikendov.embedicacardirectory.service.CarService;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableCaching
public class EmbedicaCarDirectoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbedicaCarDirectoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(CarService carService) {
		return args -> {
			carService.addCar(new Car(null, "AA999A", "ford", "black", 1986, LocalDateTime.now()));
			carService.addCar(new Car(null, "AA998A", "camaro", "red", 1970, LocalDateTime.now()));
			carService.addCar(new Car(null, "AA997A", "lada", "gray", 1999, LocalDateTime.now()));
			carService.addCar(new Car(null, "AA996A", "bmw", "green", 2001, LocalDateTime.now()));
			carService.addCar(new Car(null, "AA995A", "toyota", "yellow", 2005, LocalDateTime.now()));
			carService.addCar(new Car(null, "AA994A", "nissan", "blue", 2005, LocalDateTime.now()));
			carService.addCar(new Car(null, "AA993A", "dodge", "white", 2014, LocalDateTime.now()));
		};
	}


}
