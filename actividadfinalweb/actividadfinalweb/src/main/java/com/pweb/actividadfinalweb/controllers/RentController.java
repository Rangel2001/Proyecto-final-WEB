package com.pweb.actividadfinalweb.controllers;

import com.pweb.actividadfinalweb.dtos.rent.RentToSaveDto;
import com.pweb.actividadfinalweb.dtos.rent.RentToShowDto;
import com.pweb.actividadfinalweb.entities.Rent;
import com.pweb.actividadfinalweb.services.rent.RentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/rents")
@CrossOrigin(origins = "http://localhost:3000")
public class RentController {

    private final RentServices rentServices;

    @Autowired
    public RentController(RentServices rentServices) {
        this.rentServices = rentServices;
    }

    @PostMapping
    public ResponseEntity<RentToShowDto> saveRent(@RequestBody RentToSaveDto rentToSaveDto) {
        return ResponseEntity.ok(rentServices.saveRent(rentToSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentToShowDto> updateRent(@PathVariable Long id, @RequestBody RentToSaveDto rentToSaveDto) {
        return ResponseEntity.ok(rentServices.updateRentById(id, rentToSaveDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentToShowDto> getRentById(@PathVariable Long id) {
        return ResponseEntity.ok(rentServices.findRentById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentToShowDto>> getAllRents() {
        return ResponseEntity.ok(rentServices.findAllRents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentById(@PathVariable Long id) {
        rentServices.deleteRentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByClient")
    public ResponseEntity<List<RentToShowDto>> getRentByClientId(@RequestParam Long clientId) {
        List<Rent> rents = rentServices.findRentByClientId(clientId);
        List<RentToShowDto> rentToShowDtos = rents.stream()
                .map(rent -> rentServices.findRentById(rent.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rentToShowDtos);
    }

    @GetMapping("/searchByCar")
    public ResponseEntity<List<RentToShowDto>> getRentByCarId(@RequestParam Long carId) {
        List<Rent> rents = rentServices.findRentByCarId(carId);
        List<RentToShowDto> rentToShowDtos = rents.stream()
                .map(rent -> rentServices.findRentById(rent.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rentToShowDtos);
    }

    @GetMapping("/availability")
    public boolean checkCarAvailability(@RequestParam Long carId,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateRent,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDateRent) {
        return rentServices.isCarAvailable(carId, startDateRent, endDateRent);
    }

}
