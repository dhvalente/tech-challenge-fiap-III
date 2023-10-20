package br.com.fiap.vehicle.services.impl;

import br.com.fiap.vehicle.models.Driver;
import br.com.fiap.vehicle.repositories.DriverRepository;
import br.com.fiap.vehicle.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {

  @Autowired private DriverRepository driverRepository;

  @Override
  public Driver saveDriver(Driver driver) {
    return driverRepository.save(driver);
  }

  @Override
  public Optional<Driver> findDriverById(UUID id) {
    return driverRepository.findById(id);
  }

  @Override
  public List<Driver> findAllDrivers() {
    return driverRepository.findAll();
  }

  @Override
  public void deleteDriver(UUID id) {
    driverRepository.deleteById(id);
  }
}
