package authController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import domain.Notification;
import service.NotificationService;

import java.util.List;
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {
    @Autowired private NotificationService service;
    @GetMapping public List<Notification> all() { return service.getAll(); }
    @PostMapping public Notification send(@RequestBody Notification n) { return service.send(n); }
}