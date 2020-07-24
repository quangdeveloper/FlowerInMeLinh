package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailMimeNewDTO {
    private String subject;
    private String content;
    private List<Long> recieverIds;
    private MultipartFile[] multipartFiles;
}
