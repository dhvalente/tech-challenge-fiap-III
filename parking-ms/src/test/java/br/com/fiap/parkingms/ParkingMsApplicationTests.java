package br.com.fiap.parkingms;

import br.com.fiap.parkingms.services.impl.ParkingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ParkingMsApplicationTests {

  @Autowired private ParkingServiceImpl parkingService;

  @Test
  void contextLoads() {
    assertThat(parkingService).isNotNull();
  }
}
