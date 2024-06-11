package com.pweb.actividadfinalweb.repository;

import com.pweb.actividadfinalweb.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Encontrar carros disponibles en una locación (ciudad) específica
    List<Car> findByLocation(String location);

}
