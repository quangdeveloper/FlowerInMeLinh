package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.FlowerCategory;
@Repository

public interface FlowerCategoryRepository extends JpaRepository<FlowerCategory,Long> {

    Page<FlowerCategory> findAll(Pageable pageable);

    @Query("select u from FlowerCategory u where u.name like %:name%")
    Page<FlowerCategory> findByName(String name, Pageable pageable);

    @Query("select u from FlowerCategory u where u.code = :code")
    FlowerCategory findByCode(String code);


}
