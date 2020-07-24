package vn.chohoa.flower.email;

import com.google.common.collect.ImmutableMap;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.MailMimeNewDTO;
import vn.chohoa.flower.dto.MailSimpleNewDTO;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.model.AttackFileMail;
import vn.chohoa.flower.model.LogEmail;
import vn.chohoa.flower.model.Role;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.repository.LogEmailRepository;
import vn.chohoa.flower.repository.UserRepository;
import vn.chohoa.flower.service.LogEmailService;
import vn.chohoa.flower.util.Constant;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.*;

@Service
@Transactional
public class MailServiceImpl implements MailService {

    @Value("${config.mail.username}")
    private String email;

    @Value("${config.mail.properties.mail.amount}")
    private Integer amount;


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogEmailRepository logEmailRepository;

    @Autowired
    private LogEmailService logEmailService;

    @Override
    public void sendSimpleMail(String subject, String content, String toMail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setSubject(subject);
        message.setText(content);
        message.setTo(toMail);
        this.javaMailSender.send(message);
    }

    @Override
    public void sendMimeMail(String subject, String content, String toMail, List<AttackFileMail> list) {

        MimeMessage message = this.javaMailSender.createMimeMessage();

        try {
            /** default properties in  MimeMessageHelper: multipart = true **/
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setText(content);
            helper.setFrom(email);
            for (AttackFileMail att : list) {
                FileSystemResource file = new FileSystemResource(new File(att.getPath()));
                helper.addAttachment(file.getFilename(), file);
            }
            helper.setTo(toMail);
            this.javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void autoSendMail() {

        Pageable pageable = PageRequest.of(0, amount);
        Page<LogEmail> page = logEmailRepository.filterMailByStatusIsFalse(pageable);

        List<LogEmail> list = page.getContent();
        if (!list.isEmpty()) {
            for (LogEmail e : list) {

                logEmailService.changeStatus(e.getId());
                if (e.getType().equals(Constant.TypeEmail.SIMPLE)) {
                    for (User u : e.getReceivers()) {
                        sendSimpleMail(
                                e.getSubject(),
                                e.getContent(),
                                u.getPerson().getEmail());
                    }
                } else {
                    for (User u : e.getReceivers()) {
                        sendMimeMail(
                                e.getSubject(),
                                e.getContent(),
                                u.getPerson().getEmail(),
                                e.getAttackFileMails());
                    }
                }
            }
        }
    }
}
