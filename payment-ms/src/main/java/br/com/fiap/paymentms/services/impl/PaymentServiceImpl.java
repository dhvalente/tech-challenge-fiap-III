package br.com.fiap.paymentms.services.impl;

import br.com.fiap.paymentms.enums.PaymentStatus;
import br.com.fiap.paymentms.models.Payment;
import br.com.fiap.paymentms.repository.PaymentRepository;
import br.com.fiap.paymentms.services.PaymentService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;

  @Autowired
  public PaymentServiceImpl(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public Payment registerPayment(Payment payment) {
    payment.setPaymentDateTime(new Date());
    payment.setStatus(PaymentStatus.PENDING);
    return paymentRepository.save(payment);
  }

  @Override
  public Payment getPayment(Long paymentId) {
    return paymentRepository.findById(paymentId).orElse(null);
  }

  @Override
  public Payment updatePayment(Long paymentId, PaymentStatus status) {
    Payment paymentToUpdate = getPayment(paymentId);
    if (paymentToUpdate == null) return null;

    paymentToUpdate.setStatus(status);
    return paymentRepository.save(paymentToUpdate);
  }

  @Override
  public List<Payment> paymentHistory(String driverId) {
    return paymentRepository.findByDriverId(driverId);
  }
}
