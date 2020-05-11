package vn.chohoa.flower.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import lombok.Builder.Default;
import vn.chohoa.flower.util.SecurityUtil;

import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)//kich hoạt tựn đọng cập nhập của JPA
public abstract class AuditModel {

    @Transient
    private UserAuditModel createdByUser;

    @Column(updatable = false)
    private Long createdByUserID;

    @Column(updatable = false)
    private String createdByUsername;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Transient
    private UserAuditModel updateByUser;

    private Long updateByUserID;

    private String updateByUsername;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatAt;

    @Default
    private Boolean isActive = Boolean.TRUE;

    @PrePersist//can thiep vao trc luc tao moi
    public void beforeCreate() {
        createdByUser = SecurityUtil.getUserAuditModel();
        if (createdByUser != null) {
            createdByUserID = createdByUser.getId();
            createdByUsername = createdByUser.getUsername();
        }
    }

    @PreUpdate// can thiep vao trc luc update
    public void beforeUpdate() {
        updateByUser = SecurityUtil.getUserAuditModel();
        if (updateByUser != null) {
            updateByUserID = updateByUser.getId();
            updateByUsername = updateByUser.getUsername();
        }
    }


}
