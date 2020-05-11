package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.Flower;
@Repository
public interface FlowerRepository extends JpaRepository<Flower,Long> {

    @Query("select u from Flower  u")
    Page<Flower>  findAll(Pageable p);


    @Query("select u from Flower  u where  u.isSale = true")
    Page<Flower> findByIsSaleTrue(Pageable p);

    @Query("select u from Flower  u where  u.isSale = false")
    Page<Flower> findByIsSaleFalse(Pageable p);


}
