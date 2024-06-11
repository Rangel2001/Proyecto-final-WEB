package com.pweb.actividadfinalweb.controllers;

import com.pweb.actividadfinalweb.dtos.client.ClientToSaveDto;
import com.pweb.actividadfinalweb.dtos.client.ClientToShowDto;
import com.pweb.actividadfinalweb.services.client.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    private final ClientServices clientServices;

    @Autowired
    public ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }

    @PostMapping
    public ResponseEntity<ClientToShowDto> saveClient(@RequestBody ClientToSaveDto clientToSaveDto) {
        return ResponseEntity.ok(clientServices.saveClient(clientToSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientToShowDto> updateClient(@PathVariable Long id, @RequestBody ClientToSaveDto clientToSaveDto) {
        return ResponseEntity.ok(clientServices.updateClientById(id, clientToSaveDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientToShowDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientServices.findClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClientToShowDto>> getAllClients() {
        return ResponseEntity.ok(clientServices.findAllClients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
        clientServices.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ClientToShowDto> getClientByCedula(@RequestParam String cedula) {
        return ResponseEntity.ok(clientServices.findClientByCedula(cedula));
    }

    @GetMapping("/searchByAddress")
    public ResponseEntity<List<ClientToShowDto>> getClientByAddressContainingIgnoreCase(@RequestParam String address) {
        return ResponseEntity.ok(clientServices.findClientByAddressContainingIgnoreCase(address));
    }

}
