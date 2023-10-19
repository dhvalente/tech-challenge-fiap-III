package br.com.fiap.vehicle;

import br.com.fiap.vehicle.services.impl.VehicleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VehicleApplicationTests {

  @Autowired
  private VehicleServiceImpl vehicleService;

  @Test
  void contextLoads() {
    assertThat(vehicleService).isNotNull();
  }
}
