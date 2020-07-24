package vn.chohoa.flower.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
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

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Conversation extends BaseModel{

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    /**
     * conversation bị người dung vô hiệu hóa
     */
    @Builder.Default
    private Boolean isDelete = Boolean.FALSE;

    /**
     * check xem người nhận đã xem chưa
     */
    @Builder.Default
    private Boolean isView = Boolean.FALSE;

    /**
     * Mô tả khi có sự cố xảy ra vói conversation
     */
    private String descriptionAccident;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;


}
