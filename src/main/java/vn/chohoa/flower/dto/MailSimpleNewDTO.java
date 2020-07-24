package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailSimpleNewDTO {

    private String subject;
    private String content;
    private List<Long> recieverIds;

}
