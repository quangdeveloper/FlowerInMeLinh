package vn.chohoa.flower.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.chohoa.flower.util.SecurityUtil;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(updatable = false)
    private Long senderId;

    @Column(updatable = false)
    private String senderUsername;

    private Long editorId;

    private String editorUsername;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    private String content;

    private String fileContent;

    @CreatedDate
    private LocalDateTime receivedTime;

    @CreatedDate
    private LocalDateTime sendedTime;

    /**
     * tin nhắn bị người dung vô hiệu hóa
     */
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

    @LastModifiedDate
    private LocalDateTime editTime;

    /**
     * mã biểu tượng đang sử dụng
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Moji moji;

    /**
     * khi thêm mới conversation
     */
    @Transient
    private UserAuditModel createdByUser;

    /**
     * khi khi người nhận check đã xem tin nhắn conversation
     */
    @Transient
    private UserAuditModel updatedByUser;


    @PrePersist
    public void beforeCreate() {
        createdByUser = SecurityUtil.getUserAuditModel();
        if (createdByUser != null) {
            senderId = createdByUser.getId();
            senderUsername = createdByUser.getUsername();
        }
    }

    @PreUpdate
    public void beforeUpdate() {
        updatedByUser = SecurityUtil.getUserAuditModel();
        if (createdByUser != null) {
            editorId = updatedByUser.getId();
            editorUsername = updatedByUser.getUsername();
        }
    }
}
