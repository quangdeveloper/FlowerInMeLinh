package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.chohoa.flower.model.LogEmail;

import javax.transaction.Transactional;

//@Transactional
public interface LogEmailRepository extends JpaRepository<LogEmail,Long> {

    @Query("select log from LogEmail log where log.status = false")
    Page<LogEmail> filterMailByStatusIsFalse(Pageable p);

//    @Modifying
//    @Query(value = "update LogEmail log set log.status = true  where log.status = false",nativeQuery = true)
//    void updateLogEmail();

}
