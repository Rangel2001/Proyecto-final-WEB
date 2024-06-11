package com.pweb.actividadfinalweb.controllers;

import com.pweb.actividadfinalweb.dtos.car.CarToSaveDto;
import com.pweb.actividadfinalweb.dtos.car.CarToShowDto;
import com.pweb.actividadfinalweb.services.car.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarServices carServices;


    @Autowired
    public CarController(CarServices carServices) {
        this.carServices = carServices;
    }

    @PostMapping
    public ResponseEntity<CarToShowDto> saveCar(@RequestBody CarToSaveDto carToSaveDto) {
        return ResponseEntity.ok(carServices.saveCar(carToSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarToShowDto> updateCar(@PathVariable Long id, @RequestBody CarToSaveDto carToSaveDto) {
        return ResponseEntity.ok(carServices.updateCarById(id, carToSaveDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarToShowDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carServices.findCarById(id));
    }

    @GetMapping
    public ResponseEntity<List<CarToShowDto>> getAllCars() {
        return ResponseEntity.ok(carServices.findAllCars());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        carServices.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByLocation")
    public ResponseEntity<List<CarToShowDto>> findCarByLocation(@RequestParam String location) {
        return ResponseEntity.ok(carServices.findCarByLocation(location));
    }

}
