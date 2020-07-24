package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.chohoa.flower.model.LogUser;

public interface LogUserRepository extends JpaRepository<LogUser,Long> {

}
