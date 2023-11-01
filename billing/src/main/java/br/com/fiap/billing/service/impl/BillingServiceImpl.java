package br.com.fiap.billing.service.impl;

import br.com.fiap.billing.model.Billing;
import br.com.fiap.billing.repository.BillingRepository;
import br.com.fiap.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {

  @Autowired private BillingRepository billingRepository;

  @Override
  public List<Billing> findAll() {
    return billingRepository.findAll();
  }

  @Override
  public Billing findById(Long id) {
    Optional<Billing> billing = billingRepository.findById(id);
    return billing.orElse(null);
  }

  @Override
  public Billing save(Billing billing) {
    return billingRepository.save(billing);
  }

  @Override
  public void delete(Long id) {
    billingRepository.deleteById(id);
  }
}
