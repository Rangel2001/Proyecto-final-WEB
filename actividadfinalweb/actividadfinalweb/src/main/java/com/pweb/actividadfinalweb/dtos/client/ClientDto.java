package com.pweb.actividadfinalweb.dtos.client;

import com.pweb.actividadfinalweb.dtos.rent.RentDto;

import java.util.Collections;
import java.util.List;

public record ClientDto(

        Long id,
        String name,
        String lastName,
        String cedula,
        String address,
        String phone,
        List<RentDto> rents

) {

    public List<RentDto> rents() {
        return Collections.unmodifiableList(rents);
    }

}
