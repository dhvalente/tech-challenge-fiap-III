package br.com.fiap.vehicle.services;

import br.com.fiap.vehicle.models.Driver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface DriverService {
    Driver saveDriver(Driver driver);
    Optional<Driver> findDriverById(UUID id);
    List<Driver> findAllDrivers();
    void deleteDriver(UUID id);
    // Outros métodos relacionados a Driver...
}

