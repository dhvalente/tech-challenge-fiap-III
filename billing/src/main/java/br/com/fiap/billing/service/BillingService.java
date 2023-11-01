package br.com.fiap.billing.service;

import br.com.fiap.billing.model.Billing;

import java.util.List;

public interface BillingService {
  List<Billing> findAll();

  Billing findById(Long id);

  Billing save(Billing billing);

  void delete(Long id);
}
