package com.pweb.actividadfinalweb.services.rent;

import com.pweb.actividadfinalweb.dtos.rent.RentToSaveDto;
import com.pweb.actividadfinalweb.dtos.rent.RentToShowDto;
import com.pweb.actividadfinalweb.entities.Rent;

import java.time.LocalDate;
import java.util.List;

public interface RentServices {
    RentToShowDto saveRent(RentToSaveDto rent);
    RentToShowDto updateRentById(Long id, RentToSaveDto rent);
    RentToShowDto findRentById(Long id);
    List<RentToShowDto> findAllRents();
    void deleteRentById(Long id);
    List<Rent> findRentByClientId(Long client_id);
    List<Rent> findRentByCarId(Long car_id);
    boolean isCarAvailable(Long carId, LocalDate startDateRent, LocalDate endDateRent);
}
