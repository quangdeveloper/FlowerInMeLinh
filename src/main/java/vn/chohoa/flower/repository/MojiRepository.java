package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.chohoa.flower.model.Moji;

public interface MojiRepository extends JpaRepository<Moji, Long> {

    @Query("select u from Moji u where  u.id = :id")
    Moji findByID(Long id);

}
