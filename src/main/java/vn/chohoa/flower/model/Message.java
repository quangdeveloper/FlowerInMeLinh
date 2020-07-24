package vn.chohoa.flower.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Message extends BaseModel {

    /**mã của cuộc hội thoại*/
    @ManyToOne(fetch = FetchType.LAZY)
    private Conversation conversation;

    private String content;

    private String fileContent;

    @Builder.Default
    private Boolean isDelete = Boolean.FALSE;

    /**
     * check xem đã đc gửi chưa
     */
    @Builder.Default
    private Boolean isSend = Boolean.TRUE;

    /**
     * check xem người nhận đã xem chưa
     */
    @Builder.Default
    private Boolean isView = Boolean.FALSE;

    /**
     * Mô tả khi có sự cố xảy ra vói tin nhắn
     */
    private String descriptionAccident;

    /**
     * tin nhắn có nội dung không cho phép
     */
    private Boolean isSpam;

    private String reasonSpam;

    @ManyToOne(fetch = FetchType.LAZY)
    private Moji moji;

}
