package com.pweb.actividadfinalweb.dtos.car;

import com.pweb.actividadfinalweb.dtos.rent.RentDto;

import java.util.Collections;
import java.util.List;

public record CarDto(

        Long id,
        String model,
        String location,
        Double price,
        List<RentDto> rents

) {

    public List<RentDto> rents() {
        return Collections.unmodifiableList(rents);
    }

}
