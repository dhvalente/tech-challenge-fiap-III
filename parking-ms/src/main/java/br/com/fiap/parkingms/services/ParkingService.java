package br.com.fiap.parkingms.services;


import br.com.fiap.parkingms.enums.ParkingStatus;
import br.com.fiap.parkingms.models.Parking;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
public interface ParkingService {

    Parking save(Parking parking);

    Optional<Parking> findById(UUID id);

    List<Parking> findByDriverId(UUID driverId);

    List<Parking> findByVehicleId(UUID vehicleId);

    List<Parking> findByStatus(ParkingStatus status);

    void deleteById(UUID id);

    // Outros métodos conforme necessários podem ser adicionados aqui
}

