package br.com.fiap.parkingms.controller;

import br.com.fiap.parkingms.enums.ParkingStatus;
import br.com.fiap.parkingms.models.Parking;
import br.com.fiap.parkingms.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

  @Autowired private ParkingService parkingService;

  @PostMapping
  public ResponseEntity<Parking> registerParking(@RequestBody Parking parking) {
    Parking savedParking = parkingService.save(parking);
    return new ResponseEntity<>(savedParking, HttpStatus.CREATED);
  }

  // GET: Obter estacionamento por ID
  @GetMapping("/{id}")
  public ResponseEntity<Parking> getParkingById(@PathVariable UUID id) {
    return parkingService
        .findById(id)
        .map(parking -> new ResponseEntity<>(parking, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/driver/{driverId}")
  public ResponseEntity<List<Parking>> getParkingByDriverId(@PathVariable UUID driverId) {
    List<Parking> parkings = parkingService.findByDriverId(driverId);
    return new ResponseEntity<>(parkings, HttpStatus.OK);
  }

  @GetMapping("/vehicle/{vehicleId}")
  public ResponseEntity<List<Parking>> getParkingByVehicleId(@PathVariable UUID vehicleId) {
    List<Parking> parkings = parkingService.findByVehicleId(vehicleId);
    return new ResponseEntity<>(parkings, HttpStatus.OK);
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Parking>> getParkingByStatus(@PathVariable ParkingStatus status) {
    List<Parking> parkings = parkingService.findByStatus(status);
    return new ResponseEntity<>(parkings, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteParking(@PathVariable UUID id) {
    parkingService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
