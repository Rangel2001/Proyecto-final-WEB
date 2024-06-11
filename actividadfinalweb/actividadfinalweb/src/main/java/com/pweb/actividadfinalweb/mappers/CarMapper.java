package com.pweb.actividadfinalweb.mappers;

import com.pweb.actividadfinalweb.dtos.car.CarDto;
import com.pweb.actividadfinalweb.dtos.car.CarToSaveDto;
import com.pweb.actividadfinalweb.dtos.car.CarToShowDto;
import com.pweb.actividadfinalweb.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car carDtoToCarEntity(CarDto carDto);

    @Mapping(target = "rents", expression = "java(new ArrayList<>())")
    @Mapping(target = "rents.car", ignore = true)
    CarDto carEntityToCarDto(Car car);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rents", expression = "java(new ArrayList<>())")
    Car carToSaveDtoToCarEntity(CarToSaveDto carToSaveDto);

    CarToSaveDto carEntityToCarToSaveDto(Car car);

    @Mapping(target = "rents", ignore = true)
    Car carToShowDtoToCarEntity(CarToShowDto carToShowDto);

    CarToShowDto carEntityToCarToShowDto(Car car);

}
