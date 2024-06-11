package com.pweb.actividadfinalweb.services.rent;

import com.pweb.actividadfinalweb.dtos.rent.RentToSaveDto;
import com.pweb.actividadfinalweb.dtos.rent.RentToShowDto;
import com.pweb.actividadfinalweb.entities.Rent;
import com.pweb.actividadfinalweb.exceptions.NotFoundException;
import com.pweb.actividadfinalweb.mappers.RentMapper;
import com.pweb.actividadfinalweb.repository.CarRepository;
import com.pweb.actividadfinalweb.repository.ClientRepository;
import com.pweb.actividadfinalweb.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentServicesImpl implements RentServices {

    private final RentRepository rentRepository;

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    private final RentMapper rentMapper;

    @Autowired
    public RentServicesImpl(RentRepository rentRepository, ClientRepository clientRepository, CarRepository carRepository, RentMapper rentMapper) {
        this.rentRepository = rentRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
        this.rentMapper = rentMapper;
    }

    @Override
    public RentToShowDto saveRent(RentToSaveDto rentToSaveDto) {
        Rent rent = rentMapper.rentToSaveDtoToRentEntity(rentToSaveDto);
        rent.setClient(clientRepository.findById(rentToSaveDto.client().id())
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + rentToSaveDto.client().id())));
        rent.setCar(carRepository.findById(rentToSaveDto.car().id())
                .orElseThrow(() -> new NotFoundException("Car not found with id: " + rentToSaveDto.car().id())));
        Rent savedRent = rentRepository.save(rent);
        return rentMapper.rentEntityToRentToShowDto(savedRent);
    }

    @Override
    public RentToShowDto updateRentById(Long id, RentToSaveDto rentToSaveDto) {
        Rent rent = rentRepository.findById(id).orElseThrow(() -> new NotFoundException("Rent not found with id: " + id));
        rent.setCar(carRepository.findById(rentToSaveDto.car().id())
                .orElseThrow(() -> new NotFoundException("Car not found with id: " + rentToSaveDto.car().id())));
        rent.setClient(clientRepository.findById(rentToSaveDto.client().id())
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + rentToSaveDto.client().id())));
        Rent updatedRent = rentRepository.save(rent);
        return rentMapper.rentEntityToRentToShowDto(updatedRent);
    }

    @Override
    public RentToShowDto findRentById(Long id) {
        Rent rent = rentRepository.findById(id).orElseThrow(() -> new NotFoundException("Rent not found with id: " + id));
        return rentMapper.rentEntityToRentToShowDto(rent);
    }

    @Override
    public List<RentToShowDto> findAllRents() {
        return rentRepository.findAll().stream()
                .map(rentMapper::rentEntityToRentToShowDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRentById(Long id) {
        rentRepository.deleteById(id);
    }

    @Override
    public List<Rent> findRentByClientId(Long clientId) {
        return rentRepository.findByClientId(clientId);
    }

    @Override
    public List<Rent> findRentByCarId(Long carId) {
        return rentRepository.findByCarId(carId);
    }

    @Override
    public boolean isCarAvailable(Long carId, LocalDate startDateRent, LocalDate endDateRent) {
        List<Rent> overlappingRents = rentRepository.findOverlappingRents(carId, startDateRent, endDateRent);
        return overlappingRents.isEmpty();
    }

}
