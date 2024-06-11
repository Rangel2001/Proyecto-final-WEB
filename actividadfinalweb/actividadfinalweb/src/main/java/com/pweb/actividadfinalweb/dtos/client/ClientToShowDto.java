package com.pweb.actividadfinalweb.dtos.client;

public record ClientToShowDto(

        Long id,
        String name,
        String lastName,
        String cedula,
        String address,
        String phone

) {
}
