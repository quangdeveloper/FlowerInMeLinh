package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.chohoa.flower.model.Conversation;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface ConversationRepository extends JpaRepository<Conversation,Long> {

    @Query("select u from Conversation  u where u.name like %:name%")
    Conversation findByName(String name);

    @Query("select u from Conversation  u where u.isDelete = false")
    Page<Conversation> findAllV2(Pageable p);

    @Query("select u from Conversation  u where u.code = :code")
    Page<Conversation> findByCode(Pageable p, String code);

    @Query("select u from Conversation  u where u.id = :id")
    Conversation findByID(long id);

    @Query("select u from Conversation  u where u.id = :id and u.isDelete = true")
    Conversation findByIDAndIsDelete(long id);

    @Query("select u from Conversation  u where u.code = :code")
    Conversation findConByCode(String code);

    @Query("select u from Conversation  u where u.code = :code and u.id = :id")
    Conversation findByCodeAndId(Long id, String code);


    @Query(value = "select * from conversation c inner join conversation_users c_u on c.id = c_u.conversations_id \n" +
            "where c_u.users_id = :id and is_delete = false " +
            "order by c.id desc",nativeQuery = true)
    List<Conversation> findByUser(@Param("id") long id);

}
