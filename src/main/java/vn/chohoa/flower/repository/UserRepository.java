package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from  User  u where u.id = :id")
    User findByID(Long id);

    User findByUserName(String userName);

    User findByIdAndUserName(Long id, String username);

}
