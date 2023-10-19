package br.com.fiap.vehicle.repositories;

import br.com.fiap.vehicle.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {

}
