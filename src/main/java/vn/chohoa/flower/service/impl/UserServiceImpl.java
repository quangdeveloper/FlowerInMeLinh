package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.UserDTO;
import vn.chohoa.flower.dto.UserNewDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.UserMapper;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.repository.UserRepository;
import vn.chohoa.flower.service.UserService;
import vn.chohoa.flower.util.Constant;
import vn.chohoa.flower.util.PartnerEnum;
import vn.chohoa.flower.util.PasswordUtil;

import java.util.List;


@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService {

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

    @Override
    public ActionDTO createUser(UserNewDTO u) {

        User user = userRepository.findByUserName(u.getUserName());

        if (user != null) {

            throw new GeneralException(Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409_USER);
        }

        user = userMapper.toUserFromUserNewDTO(u);

        String password = PasswordUtil.makeRadomPassword();

        user.setPassword(passwordEncoder.encode(DigestUtils.md5DigestAsHex(password.getBytes())));

        user.setPartner(PartnerEnum.web);

        user = userRepository.save(user);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, user.getId())
                .build()
        );
    }
}
