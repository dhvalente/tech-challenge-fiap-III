package br.com.fiap.notificationms.service;

import br.com.fiap.notificationms.model.Notification;

import java.util.List;


public interface NotificationService {
  List<Notification> findAll();

  Notification findById(Long id);

  Notification save(Notification notification);

  void delete(Long id);
}
