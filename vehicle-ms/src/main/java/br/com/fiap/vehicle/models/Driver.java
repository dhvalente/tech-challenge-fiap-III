package br.com.fiap.vehicle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private String name;
  private String address;
  private String contact;

  @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Vehicle> vehicles = new ArrayList<>();
}
