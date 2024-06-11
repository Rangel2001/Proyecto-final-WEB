package com.pweb.actividadfinalweb.mappers;

import com.pweb.actividadfinalweb.dtos.client.ClientDto;
import com.pweb.actividadfinalweb.dtos.client.ClientToSaveDto;
import com.pweb.actividadfinalweb.dtos.client.ClientToShowDto;
import com.pweb.actividadfinalweb.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client clientDtoToClientEntity(ClientDto clientDto);

    @Mapping(target = "rents", expression = "java(new ArrayList<>())")
    @Mapping(target = "rents.client", ignore = true)
    ClientDto clientEntityToClientDto(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rents", expression = "java(new ArrayList<>())")
    Client clientToSaveDtoToClientEntity(ClientToSaveDto clientToSaveDto);

    ClientToSaveDto clientEntityToClientToSaveDto(Client client);

    @Mapping(target = "rents", ignore = true)
    Client clientToShowDtoToClientEntity(ClientToShowDto clientToShowDto);

    ClientToShowDto clientEntityToClientToShowDto(Client client);

}
