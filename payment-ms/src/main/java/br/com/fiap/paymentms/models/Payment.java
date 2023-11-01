package br.com.fiap.paymentms.models;

import br.com.fiap.paymentms.enums.PaymentStatus;
import br.com.fiap.paymentms.enums.PaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long paymentId;

  private String driverId;
  private String vehicleId;
  private PaymentType paymentType;
  private double amount;
  private Date paymentDateTime;
  private PaymentStatus status;
}
