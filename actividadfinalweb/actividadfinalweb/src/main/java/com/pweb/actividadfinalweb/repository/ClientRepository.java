package com.pweb.actividadfinalweb.repository;

import com.pweb.actividadfinalweb.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Encontrar cliente por cédula
    Client findByCedula(String cedula);

    // Encontrar clientes por dirección
    List<Client> findByAddressContainingIgnoreCase(String address);

}
