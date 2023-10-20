package br.com.fiap.vehicle.controller;


import br.com.fiap.vehicle.controllers.VehicleController;
import br.com.fiap.vehicle.models.Vehicle;
import br.com.fiap.vehicle.services.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {

    @Mock
    private VehicleServiceImpl vehicleService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVehicleSuccess() {
        Vehicle vehicle = new Vehicle();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(vehicleService.saveVehicle(any(Vehicle.class))).thenReturn(vehicle);

        ResponseEntity<Vehicle> response = vehicleController.createVehicle(vehicle, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createVehicleValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Vehicle> response = vehicleController.createVehicle(new Vehicle(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getVehicleByIdSuccess() {
        UUID id = UUID.randomUUID();
        Vehicle vehicle = new Vehicle();
        when(vehicleService.findVehicleById(id)).thenReturn(Optional.of(vehicle));

        ResponseEntity<Vehicle> response = vehicleController.getVehicleById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getVehicleByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(vehicleService.findVehicleById(id)).thenReturn(Optional.empty());

        ResponseEntity<Vehicle> response = vehicleController.getVehicleById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllVehiclesSuccess() {
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        when(vehicleService.findAllVehicles()).thenReturn(Arrays.asList(vehicle1, vehicle2));

        ResponseEntity<List<Vehicle>> response = vehicleController.getAllVehicles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}

