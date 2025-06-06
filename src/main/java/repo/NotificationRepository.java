package repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Notification;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}