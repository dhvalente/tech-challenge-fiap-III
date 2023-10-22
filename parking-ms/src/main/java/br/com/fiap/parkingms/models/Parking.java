package br.com.fiap.parkingms.models;

import java.time.LocalDateTime;
import java.util.UUID;


import br.com.fiap.parkingms.enums.ParkingStatus;
import br.com.fiap.parkingms.enums.ParkingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking {

  @Id
  @GeneratedValue
  private UUID id;

  private UUID driverId;

  private UUID vehicleId;

  @Enumerated(EnumType.STRING)
  private ParkingType parkingType;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  @Enumerated(EnumType.STRING)
  private ParkingStatus status;
}
