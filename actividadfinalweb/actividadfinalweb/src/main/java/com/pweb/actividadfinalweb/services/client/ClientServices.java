package com.pweb.actividadfinalweb.services.client;

import com.pweb.actividadfinalweb.dtos.client.ClientToSaveDto;
import com.pweb.actividadfinalweb.dtos.client.ClientToShowDto;

import java.util.List;

public interface ClientServices {
    ClientToShowDto saveClient(ClientToSaveDto client);
    ClientToShowDto updateClientById(Long id, ClientToSaveDto client);
    ClientToShowDto findClientById(Long id);
    List<ClientToShowDto> findAllClients();
    void deleteClientById(Long id);
    ClientToShowDto findClientByCedula(String cedula);
    List<ClientToShowDto> findClientByAddressContainingIgnoreCase(String address);

}
