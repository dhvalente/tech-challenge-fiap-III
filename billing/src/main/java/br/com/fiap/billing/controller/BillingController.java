package br.com.fiap.billing.controller;

import br.com.fiap.billing.model.Billing;
import br.com.fiap.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api//billings")
public class BillingController {

  @Autowired private BillingService billingService;

  @GetMapping
  public List<Billing> getAllBillings() {
    return billingService.findAll();
  }

  @GetMapping("/{id}")
  public Billing getBillingById(@PathVariable Long id) {
    return billingService.findById(id);
  }

  @PostMapping
  public Billing createBilling(@RequestBody Billing billing) {
    return billingService.save(billing);
  }

  @DeleteMapping("/{id}")
  public void deleteBilling(@PathVariable Long id) {
    billingService.delete(id);
  }
}
