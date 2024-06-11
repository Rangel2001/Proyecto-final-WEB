package com.pweb.actividadfinalweb.mappers;

import com.pweb.actividadfinalweb.dtos.rent.RentDto;
import com.pweb.actividadfinalweb.dtos.rent.RentToSaveDto;
import com.pweb.actividadfinalweb.dtos.rent.RentToShowDto;
import com.pweb.actividadfinalweb.entities.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentMapper {

    Rent rentDtoToRentEntity(RentDto rentDto);

    @Mapping(target = "car", ignore = true)
    @Mapping(target = "client", ignore = true)
    RentDto rentEntityToRentDto(Rent rent);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car.rents", expression = "java(new ArrayList<Rent>())")
    @Mapping(target = "client.rents", expression = "java(new ArrayList<Rent>())")
    Rent rentToSaveDtoToRentEntity(RentToSaveDto rentToSaveDto);

    RentToSaveDto rentEntityToRentToSaveDto(Rent rent);

    @Mapping(target = "car.rents", ignore = true)
    @Mapping(target = "client.rents", ignore = true)
    Rent rentToShowDtoToRentEntity(RentToShowDto rentToShowDto);

    RentToShowDto rentEntityToRentToShowDto(Rent rent);

}
