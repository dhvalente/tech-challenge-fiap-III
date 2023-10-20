package br.com.fiap.vehicle.services;

import br.com.fiap.vehicle.models.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
    Optional<Vehicle> findVehicleById(UUID id);
    List<Vehicle> findAllVehicles();
    void deleteVehicle(UUID id);
    List<Vehicle> findVehiclesByDriverId(UUID driverId);

}

