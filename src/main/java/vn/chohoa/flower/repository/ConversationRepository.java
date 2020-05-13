package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.chohoa.flower.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("select u from Conversation  u where code = :code")
    Page<Conversation> findByCode(Pageable p, String code);

    @Query("select u from Conversation  u where code = :code and u.id = :id")
    Conversation findByCodeAndId(Long id, String code);

}
