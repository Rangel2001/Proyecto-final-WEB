package com.pweb.actividadfinalweb.dtos.client;

public record ClientToSaveDto(

        String name,
        String lastName,
        String cedula,
        String address,
        String phone

) {
}
