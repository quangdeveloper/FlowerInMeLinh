package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);

}
