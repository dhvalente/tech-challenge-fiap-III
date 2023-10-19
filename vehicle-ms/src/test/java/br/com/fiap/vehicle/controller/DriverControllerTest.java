package br.com.fiap.vehicle.controller;


import br.com.fiap.vehicle.controllers.DriverController;
import br.com.fiap.vehicle.models.Driver;
import br.com.fiap.vehicle.services.impl.DriverServiceImpl;
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

class DriverControllerTest {

    @Mock
    private DriverServiceImpl driverService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private DriverController driverController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDriverSuccess() {
        Driver driver = new Driver();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(driverService.saveDriver(any(Driver.class))).thenReturn(driver);

        ResponseEntity<Driver> response = driverController.createDriver(driver, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createDriverValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Driver> response = driverController.createDriver(new Driver(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getDriverByIdSuccess() {
        UUID id = UUID.randomUUID();
        Driver driver = new Driver();
        when(driverService.findDriverById(id)).thenReturn(Optional.of(driver));

        ResponseEntity<Driver> response = driverController.getDriverById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getDriverByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(driverService.findDriverById(id)).thenReturn(Optional.empty());

        ResponseEntity<Driver> response = driverController.getDriverById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllDriversSuccess() {
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        when(driverService.findAllDrivers()).thenReturn(Arrays.asList(driver1, driver2));

        ResponseEntity<List<Driver>> response = driverController.getAllDrivers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void updateDriverSuccess() {
        UUID id = UUID.randomUUID();
        Driver driver = new Driver();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(driverService.findDriverById(id)).thenReturn(Optional.of(driver));
        when(driverService.saveDriver(any(Driver.class))).thenReturn(driver);

        ResponseEntity<Driver> response = driverController.updateDriver(id, driver, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateDriverValidationError() {
        UUID id = UUID.randomUUID();
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Driver> response = driverController.updateDriver(id, new Driver(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateDriverNotFound() {
        UUID id = UUID.randomUUID();
        Driver driver = new Driver();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(driverService.findDriverById(id)).thenReturn(Optional.empty());

        ResponseEntity<Driver> response = driverController.updateDriver(id, driver, bindingResult);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteDriverSuccess() {
        UUID id = UUID.randomUUID();
        when(driverService.findDriverById(id)).thenReturn(Optional.of(new Driver()));

        ResponseEntity<Void> response = driverController.deleteDriver(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteDriverNotFound() {
        UUID id = UUID.randomUUID();
        when(driverService.findDriverById(id)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = driverController.deleteDriver(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

