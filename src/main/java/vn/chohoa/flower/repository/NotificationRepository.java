package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.chohoa.flower.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {


}
