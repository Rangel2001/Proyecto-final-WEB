package com.pweb.actividadfinalweb.services.client;

import com.pweb.actividadfinalweb.dtos.client.ClientToSaveDto;
import com.pweb.actividadfinalweb.dtos.client.ClientToShowDto;
import com.pweb.actividadfinalweb.entities.Client;
import com.pweb.actividadfinalweb.exceptions.NotFoundException;
import com.pweb.actividadfinalweb.mappers.ClientMapper;
import com.pweb.actividadfinalweb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServicesImpl implements ClientServices {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientServicesImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientToShowDto saveClient(ClientToSaveDto clientToSaveDto) {
        Client client = clientMapper.clientToSaveDtoToClientEntity(clientToSaveDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.clientEntityToClientToShowDto(savedClient);
    }

    @Override
    public ClientToShowDto updateClientById(Long id, ClientToSaveDto clientToSaveDto) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        client.setName(clientToSaveDto.name());
        client.setLastName(clientToSaveDto.lastName());
        client.setCedula(clientToSaveDto.cedula());
        client.setAddress(clientToSaveDto.address());
        client.setPhone(clientToSaveDto.phone());
        Client updatedClient = clientRepository.save(client);
        return clientMapper.clientEntityToClientToShowDto(updatedClient);
    }

    @Override
    public ClientToShowDto findClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        return clientMapper.clientEntityToClientToShowDto(client);
    }

    @Override
    public List<ClientToShowDto> findAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::clientEntityToClientToShowDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientToShowDto findClientByCedula(String cedula) {
        Client client = clientRepository.findByCedula(cedula);
        if (client == null) {
            throw new NotFoundException("Client not found with cedula: " + cedula);
        }
        return clientMapper.clientEntityToClientToShowDto(client);
    }

    @Override
    public List<ClientToShowDto> findClientByAddressContainingIgnoreCase(String address) {
        return clientRepository.findByAddressContainingIgnoreCase(address).stream()
                .map(clientMapper::clientEntityToClientToShowDto)
                .collect(Collectors.toList());
    }

}
