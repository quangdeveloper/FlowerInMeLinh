package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByCode(String code);

    @Query("select u from Role  u where u.id = :id")
    Role findOne(long id);
}
