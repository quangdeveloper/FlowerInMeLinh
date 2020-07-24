package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.chohoa.flower.model.Conversation;
import vn.chohoa.flower.model.Message;

import javax.transaction.Transactional;
import java.util.List;

/** them @transaction trong class để thực thi đc câu lênh query native*/
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select u from Message  u where u.conversation = :con and u.isDelete = false")
    Page<Message> findByConversation(Pageable p,Conversation con);

//    @Query("select u from Message  u where u.conversation = :con")
//    Page<Message> findByConversationAndIsDelete(Pageable p,Conversation con);

    @Query("select u from Message  u where u.conversation = :con")
    List<Message> findByConversation(Conversation con);


    @Query("select u from Conversation u where  u.id = :id")
    Message findByID(Long id);

    /** thêm @Modifying để jdbc hỏi là câu lênh dưới có thể là insert delete update*/
    @Modifying
    @Query(value = "update message set is_delete  = 1 where conversation_id = :id",nativeQuery = true)
    void deleteMessageByConversationId(Long id);

}
