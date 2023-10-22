package br.com.fiap.parkingms.repository;

import br.com.fiap.parkingms.enums.ParkingStatus;
import br.com.fiap.parkingms.models.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, UUID> {

  List<Parking> findByDriverId(UUID driverId);

  List<Parking> findByVehicleId(UUID vehicleId);

  List<Parking> findByStatus(ParkingStatus status);

  Optional<Parking> findById(UUID id);
}
