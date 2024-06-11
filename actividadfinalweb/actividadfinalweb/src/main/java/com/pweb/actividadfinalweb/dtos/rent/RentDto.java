package com.pweb.actividadfinalweb.dtos.rent;

import com.pweb.actividadfinalweb.dtos.car.CarDto;
import com.pweb.actividadfinalweb.dtos.client.ClientDto;

import java.time.LocalDate;

public record RentDto(

        Long id,
        LocalDate startDateRent,
        LocalDate endDateRent,
        CarDto car,
        ClientDto client

) {
}
