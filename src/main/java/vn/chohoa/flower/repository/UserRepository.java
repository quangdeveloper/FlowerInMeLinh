package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.User;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from  User  u where u.id = :id")
    User findByID(Long id);

    User findByUserName(String userName);

    @Query("select u from  User  u where u.id = :id and u.userName = :username")
    User findByIdAndUserName(Long id, String username);

    @Modifying
    @Query(value = "update User u set u.avatar =: avatar where u.id =: id", nativeQuery = true)
    User changeAvatar(String avatar,
                      Long id);

}
