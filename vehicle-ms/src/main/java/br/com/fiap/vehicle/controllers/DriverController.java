package br.com.fiap.vehicle.controllers;

import br.com.fiap.vehicle.models.Driver;
import br.com.fiap.vehicle.services.impl.DriverServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class DriverController {


  private static final String DRIVER_NOT_FOUND_MSG = "Driver with ID: {} not found";


  @Autowired private DriverServiceImpl driverService;

  @PostMapping
  public ResponseEntity<Driver> createDriver(
      @Valid @RequestBody Driver driver, BindingResult result) {
    if (result.hasErrors()) {
      log.error("Error creating driver: {}", result.getAllErrors());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Driver savedDriver = driverService.saveDriver(driver);
    log.info("Driver created with ID: {}", savedDriver.getId());
    return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Driver> getDriverById(@PathVariable UUID id) {
    Optional<Driver> driver = driverService.findDriverById(id);
    if (driver.isPresent()) {
      return new ResponseEntity<>(driver.get(), HttpStatus.OK);
    } else {
      log.warn(DRIVER_NOT_FOUND_MSG, id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  public ResponseEntity<List<Driver>> getAllDrivers() {
    List<Driver> drivers = driverService.findAllDrivers();
    return new ResponseEntity<>(drivers, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Driver> updateDriver(
      @PathVariable UUID id, @Valid @RequestBody Driver driver, BindingResult result) {
    if (result.hasErrors()) {
      log.error("Error updating driver: {}", result.getAllErrors());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    if (driverService.findDriverById(id).isPresent()) {
      driver.setId(id);
      Driver updatedDriver = driverService.saveDriver(driver);
      log.info("Driver with ID: {} updated", id);
      return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    } else {
      log.warn(DRIVER_NOT_FOUND_MSG, id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDriver(@PathVariable UUID id) {
    if (driverService.findDriverById(id).isPresent()) {
      driverService.deleteDriver(id);
      log.info("Driver with ID: {} deleted", id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      log.warn(DRIVER_NOT_FOUND_MSG,  id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
