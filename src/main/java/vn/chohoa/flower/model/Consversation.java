package vn.chohoa.flower.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import vn.chohoa.flower.util.SecurityUtil;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class Consversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long code;

    @Column(updatable = false)
    private String senderUsername;


    private String receiverUsername;

    @Column(updatable = false)
    private Long senderId;

    private Long receiverId;

    private String senderName;

    private String receiverName;

    @Min(1)
    @NotNull
    private String content;

    private byte[] fileContent;


    private LocalDateTime receivedTime;

    @NotNull
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

    /**
     * mã biểu tượng đang sử dụng
     */
    private long idMoji;

    /**
     * mã nhóm biểu tượng đang sử dụng
     */
    private Long idGroupMoji;


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

}
