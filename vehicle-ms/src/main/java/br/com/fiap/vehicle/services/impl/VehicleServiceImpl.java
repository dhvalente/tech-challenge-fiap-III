package br.com.fiap.vehicle.services.impl;

import br.com.fiap.vehicle.models.Vehicle;
import br.com.fiap.vehicle.repositories.VehicleRepository;
import br.com.fiap.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {
  @Autowired private VehicleRepository vehicleRepository;

  @Override
  public Vehicle saveVehicle(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }

  @Override
  public Optional<Vehicle> findVehicleById(UUID id) {
    return vehicleRepository.findById(id);
  }

  @Override
  public List<Vehicle> findAllVehicles() {
    return vehicleRepository.findAll();
  }

  @Override
  public void deleteVehicle(UUID id) {
    vehicleRepository.deleteById(id);
  }

  @Override
  public List<Vehicle> findVehiclesByDriverId(UUID driverId) {
    return vehicleRepository.findByDriverId(driverId);
  }
}
