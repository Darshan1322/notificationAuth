package service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service; // ✅ Required annotation

import domain.Notification;
import repo.NotificationRepository;

@Service  // ✅ This tells Spring to treat this as a bean
public class NotificationService {
    
    @Autowired
    private NotificationRepository repo;
    @Autowired 
    private JavaMailSender mailSender;
    public Notification send(Notification n) {
        try {
            if ("EMAIL".equalsIgnoreCase(n.getChannel())) {
                sendEmail(n.getReceiver(), "Notification", n.getContent());
            }
            n.setStatus("Sent");
        } catch (Exception e) {
            n.setStatus("Failed");
        }
        n.setTimestamp(LocalDateTime.now());
        return repo.save(n);
    }
    
    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public List<Notification> getAll() {
        return repo.findAll();
    }
}
