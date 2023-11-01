package br.com.fiap.paymentms.controller;

import br.com.fiap.paymentms.enums.PaymentStatus;
import br.com.fiap.paymentms.models.Payment;
import br.com.fiap.paymentms.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

  private final PaymentService paymentService;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
    Payment newPayment = paymentService.registerPayment(payment);
    return ResponseEntity.ok(newPayment);
  }

  @GetMapping("/{paymentId}")
  public ResponseEntity<Payment> getPayment(@PathVariable Long paymentId) {
    Payment payment = paymentService.getPayment(paymentId);
    if (payment != null) {
      return ResponseEntity.ok(payment);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{paymentId}")
  public ResponseEntity<Payment> updatePayment(
      @PathVariable Long paymentId, @RequestBody PaymentStatus status) {
    Payment updatedPayment = paymentService.updatePayment(paymentId, status);
    if (updatedPayment != null) {
      return ResponseEntity.ok(updatedPayment);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/history/{driverId}")
  public ResponseEntity<List<Payment>> getPaymentHistory(@PathVariable String driverId) {
    List<Payment> payments = paymentService.paymentHistory(driverId);
    return ResponseEntity.ok(payments);
  }
}
