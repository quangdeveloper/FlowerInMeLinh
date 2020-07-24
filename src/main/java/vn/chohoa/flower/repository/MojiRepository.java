package vn.chohoa.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.chohoa.flower.model.Moji;

public interface MojiRepository extends JpaRepository<Moji, Long> {

    @Query("select u from Moji u where  u.id = :id")
    Moji findByID(Long id);

    @Query("select u from Moji u where  u.groupId = :id and u.groupName = :name")
    Moji findByGroupIdAndName(Long id,String name);

    @Query("select u from Moji u where  u.groupId = :id ")
    Moji findByGroupId(Long id);


    @Query("select u from Moji u where  u.id = :id and u.name = :name and u.code = :code")
    Moji findByIdAndNameAndCode(Long id,String name,String code);


    @Query("select u from Moji u where  u.linkMoji = :link")
    Moji findByLinkMoji(String link);

    @Query("select u from Moji u where  u.name = :name")
    Moji findByName(String name);

}
