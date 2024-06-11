package com.pweb.actividadfinalweb.dtos.rent;

import com.pweb.actividadfinalweb.dtos.car.CarToShowDto;
import com.pweb.actividadfinalweb.dtos.client.ClientToShowDto;

import java.time.LocalDate;

public record RentToShowDto(

        Long id,
        LocalDate startDateRent,
        LocalDate endDateRent,
        CarToShowDto car,
        ClientToShowDto client

) {
}
