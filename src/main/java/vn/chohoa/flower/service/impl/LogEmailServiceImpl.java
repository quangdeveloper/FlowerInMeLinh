package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.EmailNewDTO;
import vn.chohoa.flower.email.MailService;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.LogEmailMapper;
import vn.chohoa.flower.model.AttackFileMail;
import vn.chohoa.flower.model.LogEmail;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.repository.AttackFileEmailRepository;
import vn.chohoa.flower.repository.LogEmailRepository;
import vn.chohoa.flower.repository.UserRepository;
import vn.chohoa.flower.service.LogEmailService;
import vn.chohoa.flower.util.Constant;
import vn.chohoa.flower.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LogEmailServiceImpl implements LogEmailService {

    @Autowired
    private LogEmailMapper emailMapper;

    @Autowired
    private LogEmailRepository emailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttackFileMailServiceImpl attackFileMailService;

    @Value("${dir.attackMail}")
    private String dirFile;

    @Override
    public ActionDTO insertLogEmail(EmailNewDTO e) {

        if (e.getRecieverIds() == null || e.getRecieverIds().isEmpty()) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404, Constant.RESPONSE.MESSAGE.C404_INVALID_RECIECER);
        }
        LogEmail email = emailMapper.toLogEmailFromEmailNewDTO(e);
        List<User> users = new ArrayList<>();
        e.getRecieverIds().forEach(id -> {
                    User user = userRepository.findById(id).orElseThrow(
                            () -> new GeneralException(Constant.RESPONSE.CODE.C404, Constant.RESPONSE.MESSAGE.C404_USER, id));
                    users.add(user);
                }
        );
        email.setReceivers(users);
        if (e.getMultipartFile() == null){
            email.setType(Constant.TypeEmail.SIMPLE);
        }else{
            email.setType(Constant.TypeEmail.MIME);
            List<AttackFileMail> attackFileMails = new ArrayList<>();
            for (MultipartFile file : e.getMultipartFile()) {
                String name = FileUtil.multipartfileToFile(file, dirFile);
                AttackFileMail attack = AttackFileMail.builder()
                        .logEmail(email)
                        .path(name)
                        .build();
                attackFileMails.add(attack);
            }
            email.setAttackFileMails(attackFileMails);
        }
        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, emailRepository.save(email).getId())
                .build());
    }

    @Override
    public void changeStatus(Long id) {
        LogEmail logEmail = emailRepository.findById(id).orElseThrow(
                ()-> new GeneralException(Constant.RESPONSE.CODE.C404,Constant.RESPONSE.MESSAGE.C404_EMAIL)
        );
        logEmail.setStatus(true);
        emailRepository.save(logEmail);
    }
}
