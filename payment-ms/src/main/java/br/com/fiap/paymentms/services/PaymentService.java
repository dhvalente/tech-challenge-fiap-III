package br.com.fiap.paymentms.services;

import br.com.fiap.paymentms.enums.PaymentStatus;
import br.com.fiap.paymentms.models.Payment;

import java.util.List;

public interface PaymentService {
  Payment registerPayment(Payment payment);

  Payment getPayment(Long paymentId);

  Payment updatePayment(Long paymentId, PaymentStatus status);

  List<Payment> paymentHistory(String driverId);
}
