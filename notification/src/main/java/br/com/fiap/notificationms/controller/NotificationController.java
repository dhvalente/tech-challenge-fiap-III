package br.com.fiap.notificationms.controller;

import br.com.fiap.notificationms.model.Notification;
import br.com.fiap.notificationms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

  @Autowired private NotificationService notificationService;

  @GetMapping
  public List<Notification> getAllNotifications() {
    return notificationService.findAll();
  }

  @GetMapping("/{id}")
  public Notification getNotificationById(@PathVariable Long id) {
    return notificationService.findById(id);
  }

  @PostMapping
  public Notification createNotification(@RequestBody Notification notification) {
    return notificationService.save(notification);
  }

  @DeleteMapping("/{id}")
  public void deleteNotification(@PathVariable Long id) {
    notificationService.delete(id);
  }
}
