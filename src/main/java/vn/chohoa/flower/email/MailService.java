package vn.chohoa.flower.email;

import vn.chohoa.flower.model.AttackFileMail;

import java.util.List;

public interface MailService {
    void sendSimpleMail(String subject, String content, String toMail);
    void sendMimeMail(String subject, String content, String toMail, List<AttackFileMail> list);
    void autoSendMail();
}
