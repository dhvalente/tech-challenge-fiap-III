package br.com.fiap.notificationms.service.impl;

import br.com.fiap.notificationms.model.Notification;
import br.com.fiap.notificationms.repository.NotificationRepository;
import br.com.fiap.notificationms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired private NotificationRepository notificationRepository;

  @Override
  public List<Notification> findAll() {
    return notificationRepository.findAll();
  }

  @Override
  public Notification findById(Long id) {
    Optional<Notification> notification = notificationRepository.findById(id);
    return notification.orElse(null);
  }

  @Override
  public Notification save(Notification notification) {
    return notificationRepository.save(notification);
  }

  @Override
  public void delete(Long id) {
    notificationRepository.deleteById(id);
  }
}
