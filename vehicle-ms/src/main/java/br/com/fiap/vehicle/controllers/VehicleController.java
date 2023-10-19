package br.com.fiap.vehicle.controllers;

import br.com.fiap.vehicle.models.Vehicle;
import br.com.fiap.vehicle.services.impl.VehicleServiceImpl;
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
@RequestMapping("/api/vehicles")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle, BindingResult result) {
        if (result.hasErrors()) {
            log.error("Error creating vehicle: {}", result.getAllErrors());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        log.info("Vehicle created with ID: {}", savedVehicle.getId());
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable UUID id) {
        Optional<Vehicle> vehicle = vehicleService.findVehicleById(id);
        if (vehicle.isPresent()) {
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        } else {
            log.warn("Vehicle with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }


}
