package com.pweb.actividadfinalweb.services.car;

import com.pweb.actividadfinalweb.dtos.car.CarToSaveDto;
import com.pweb.actividadfinalweb.dtos.car.CarToShowDto;
import com.pweb.actividadfinalweb.entities.Car;
import com.pweb.actividadfinalweb.exceptions.NotFoundException;
import com.pweb.actividadfinalweb.mappers.CarMapper;
import com.pweb.actividadfinalweb.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServicesImpl implements CarServices {

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    @Autowired
    public CarServicesImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarToShowDto saveCar(CarToSaveDto carToSaveDto) {
        Car car = carMapper.carToSaveDtoToCarEntity(carToSaveDto);
        Car savedCar = carRepository.save(car);
        return carMapper.carEntityToCarToShowDto(savedCar);
    }

    @Override
    public CarToShowDto updateCarById(Long id, CarToSaveDto carToSaveDto) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found with id: " + id));
        car.setModel(carToSaveDto.model());
        car.setLocation(carToSaveDto.location());
        car.setPrice(carToSaveDto.price());
        Car updatedCar = carRepository.save(car);
        return carMapper.carEntityToCarToShowDto(updatedCar);
    }

    @Override
    public CarToShowDto findCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found with id: " + id));
        return carMapper.carEntityToCarToShowDto(car);
    }

    @Override
    public List<CarToShowDto> findAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::carEntityToCarToShowDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarToShowDto> findCarByLocation(String location) {
        return carRepository.findByLocation(location).stream()
                .map(carMapper::carEntityToCarToShowDto)
                .collect(Collectors.toList());
    }

}
