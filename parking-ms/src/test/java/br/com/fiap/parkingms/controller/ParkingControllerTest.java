package br.com.fiap.parkingms.controller;

import br.com.fiap.parkingms.models.Parking;
import br.com.fiap.parkingms.services.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import br.com.fiap.parkingms.enums.ParkingStatus;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    private Parking parking;

    @BeforeEach
    public void setUp() {
        parking = new Parking();
        parking.setId(UUID.randomUUID());
        parking.setStatus(ParkingStatus.STARTED);
    }

    @Test
    void registerParkingTest() throws Exception {
        when(parkingService.save(any())).thenReturn(parking);

        mockMvc.perform(post("/api/parking")
                        .content(new ObjectMapper().writeValueAsString(parking))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getParkingByIdTest() throws Exception {
        when(parkingService.findById(parking.getId())).thenReturn(Optional.of(parking));

        mockMvc.perform(get("/api/parking/" + parking.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getParkingByNonExistingIdTest() throws Exception {
        when(parkingService.findById(parking.getId())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/parking/" + parking.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getParkingByDriverIdTest() throws Exception {
        when(parkingService.findByDriverId(any())).thenReturn(Collections.singletonList(parking));

        mockMvc.perform(get("/api/parking/driver/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteParkingTest() throws Exception {
        when(parkingService.findById(parking.getId())).thenReturn(Optional.of(parking));

        mockMvc.perform(delete("/api/parking/" + parking.getId()))
                .andExpect(status().isNoContent());
    }


}

