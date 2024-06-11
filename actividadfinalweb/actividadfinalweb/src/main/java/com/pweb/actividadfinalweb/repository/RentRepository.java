package com.pweb.actividadfinalweb.repository;

import com.pweb.actividadfinalweb.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    // Encontrar renta por cliente
    List<Rent> findByClientId(Long clientId);

    // Encontrar renta por carro
    List<Rent> findByCarId(Long carId);

    @Query("SELECT r FROM Rent r WHERE r.car.id = :carId AND " +
            "(:startDateRent BETWEEN r.startDateRent AND r.endDateRent OR " +
            ":endDateRent BETWEEN r.startDateRent AND r.endDateRent OR " +
            "r.startDateRent BETWEEN :startDateRent AND :endDateRent)")
    List<Rent> findOverlappingRents(Long carId, LocalDate startDateRent, LocalDate endDateRent);

}
