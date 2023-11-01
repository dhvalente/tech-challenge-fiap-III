package br.com.fiap.paymentms.repository;

import br.com.fiap.paymentms.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  List<Payment> findByDriverId(String driverId);
}
