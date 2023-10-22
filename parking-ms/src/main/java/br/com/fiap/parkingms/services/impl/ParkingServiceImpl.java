package br.com.fiap.parkingms.services.impl;

import br.com.fiap.parkingms.enums.ParkingStatus;
import br.com.fiap.parkingms.models.Parking;
import br.com.fiap.parkingms.repository.ParkingRepository;
import br.com.fiap.parkingms.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingServiceImpl implements ParkingService {

  @Autowired private ParkingRepository parkingRepository;

  @Override
  public Parking save(Parking parking) {
    return parkingRepository.save(parking);
  }

  @Override
  public Optional<Parking> findById(UUID id) {
    return parkingRepository.findById(id);
  }

  @Override
  public List<Parking> findByDriverId(UUID driverId) {
    return parkingRepository.findByDriverId(driverId);
  }

  @Override
  public List<Parking> findByVehicleId(UUID vehicleId) {
    return parkingRepository.findByVehicleId(vehicleId);
  }

  @Override
  public List<Parking> findByStatus(ParkingStatus status) {
    return parkingRepository.findByStatus(status);
  }

  @Override
  public void deleteById(UUID id) {
    parkingRepository.deleteById(id);
  }
}
