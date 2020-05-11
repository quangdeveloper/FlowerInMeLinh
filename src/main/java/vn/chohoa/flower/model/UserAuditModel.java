package vn.chohoa.flower.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserAuditModel {

    private Long id;

    private String username;
}

