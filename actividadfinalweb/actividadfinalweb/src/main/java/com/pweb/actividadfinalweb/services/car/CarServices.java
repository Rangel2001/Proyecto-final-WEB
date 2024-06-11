package com.pweb.actividadfinalweb.services.car;

import com.pweb.actividadfinalweb.dtos.car.CarToSaveDto;
import com.pweb.actividadfinalweb.dtos.car.CarToShowDto;

import java.util.List;

public interface CarServices {
    CarToShowDto saveCar(CarToSaveDto car);
    CarToShowDto updateCarById(Long id, CarToSaveDto car);
    CarToShowDto findCarById(Long id);
    List<CarToShowDto> findAllCars();
    void deleteCarById(Long id);
    List<CarToShowDto> findCarByLocation(String location);

}
