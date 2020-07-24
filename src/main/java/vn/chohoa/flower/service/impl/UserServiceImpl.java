package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.UserMapper;
import vn.chohoa.flower.model.Role;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.repository.UserRepository;
import vn.chohoa.flower.service.UserService;
import vn.chohoa.flower.util.Constant;
import vn.chohoa.flower.util.FileUtil;
import vn.chohoa.flower.util.PartnerEnum;
import vn.chohoa.flower.util.PasswordUtil;

import java.util.List;


@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${dir.avatar}")
    private String dirAvatar;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User updateToken(String username, String partner, String token) {

        final User user = userRepository.findByUserName(username);

        user.setPartner(PartnerEnum.valueOf(partner));

        user.setFirebaseToken(token);

        return userRepository.save(user);
    }

    @Override
    public PageDTO getListUser(PageParam p) {
        User user = userRepository.findByID(1L);
        List<Role> roles = user.getRoles();

        Pageable pageable = PageRequest.of(
                p.getPageNo() - 1,
                p.getPageSize(),
                Sort.by("id").descending()
        );
        final Page<User> page = userRepository.findAll(pageable);

        List<UserDTO> list = page.map(userMapper::toUserDTOFromUser).getContent();

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

//    public PageDTO getListUserConversationDTO(PageParam p) {
//
//        Pageable pageable = PageRequest.of(
//                p.getPageNo() - 1,
//                p.getPageSize(),
//                Sort.by("id").descending()
//        );
//
//
//        final Page<User> page = userRepository.findAll(pageable);
//
//        List<UserConversationDTO> list = page.map(userMapper::toUserConversationDtoFromUser).getContent();
//
//        final long total = page.getTotalElements();
//
//        return PageDTO.builder()
//                .content(list)
//                .total(total)
//                .build();
//    }

    @Override
    public ActionDTO createUser(UserNewDTO u) {

        User user = userRepository.findByUserName(u.getUserName());

        if (user != null) {

            throw new GeneralException(Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409_USER);
        }

        user = userMapper.toUserFromUserNewDTO(u);

        user.setPassword(passwordEncoder.encode(u.getPassword()));

        user.setIsDeleted(false);

        user.setIsActive(true);

        user.setIsNeedChangePass(false);

        user.setIsWarningSpam(false);

        user.setPartner(PartnerEnum.web);

        user = userRepository.save(user);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, user.getId())
                .build()
        );
    }

    @Override
    public ActionDTO changeAvatar(UserChangeAvatarDTO user) {

        User old = userRepository.findById(user.getId()).orElseThrow(
                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_USER)
        );
        userRepository.changeAvatar(FileUtil.multipartfileToFile(user.getAvatar(),this.dirAvatar),user.getId());
        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, user.getId())
                .build());
    }
}
