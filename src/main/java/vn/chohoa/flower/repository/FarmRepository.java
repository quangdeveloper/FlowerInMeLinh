package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.Farm;
@Repository
public interface FarmRepository extends JpaRepository<Farm,Long> {

    @Query("select u from Farm u where (:name is null or u.name like %:name% )" +
            "and (:owner is null or u.owner like %:owner%)" +
            "and (:address is null or u.address like %:address%)" +
            "and (:isActive is null or u.isActive = :isActive)")
    Page<Farm> findByNameAndOwnerAndAddressAndIsActive(Pageable p,
                                                       String name,
                                                       String owner,
                                                       String address,
                                                       Boolean isActive);


    @Query("select u from Farm  u where u.code = :code")
    Farm findByCode(String code);


    @Query("select u from Farm  u where u.isActive = true")
    Page<Farm> findByIsAcgtiveTrue(Pageable p);

    @Query("select u from Farm  u where u.isActive = false")
    Page<Farm> findByIsAcgtiveFalse(Pageable p);

}
