package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.DeleteParam;
import vn.chohoa.flower.dto.apiParam.GetConversationParam;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.ConversationMapper;
import vn.chohoa.flower.model.Conversation;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.model.UserAuditModel;
import vn.chohoa.flower.repository.ConversationRepository;
import vn.chohoa.flower.repository.MessageRepository;
import vn.chohoa.flower.repository.UserRepository;
import vn.chohoa.flower.service.ConversationService;
import vn.chohoa.flower.util.Constant;
import vn.chohoa.flower.util.SecurityUtil;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;


    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PageDTO findAll(PageParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo() - 1, p.getPageSize(), Sort.by("id").descending());

        final Page<Conversation> page = conversationRepository.findAllV2(pageable);

        List<ConversationDTO> list = page.map(conversationMapper::toConversationDtoFromConversation).getContent();

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

    @Override
    public RsDTO findbyUserId(GetConversationParam p) {

        List<ConversationDTO> list = conversationMapper.toConversationDtoFromConversation(
                conversationRepository.findByUser(p.getId())
        );

        return new RsDTO(list);
    }

    @Override
    public ActionDTO createConversation(ConversationNewDTO con) {

        if (conversationRepository.findByName(con.getName()) != null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409_CONVERSATION);
        }

        /** kiểm tra số lượng user*/
        if (con.getUserIds().size() < 2) {
            throw new GeneralException(Constant.RESPONSE.CODE.C801,
                    Constant.RESPONSE.MESSAGE.C801_TOTAL_MENBER);
        }

        List<User> users = userRepository.findAll();

        List<Long> userIds = new ArrayList<>();

        users.forEach(u ->
                userIds.add(u.getId()));

        if (!userIds.containsAll(con.getUserIds())) {

            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_USER);
        }

        Conversation conversation = conversationMapper.toConversationFromConversationNewDTO(con);

        List<User> newUsers = new ArrayList<>();

        for (Long i : con.getUserIds()) {
            newUsers.add(userRepository.findByID(i));
        }

        conversation.setUsers(newUsers);

        conversation.setCode(makeCode(con.getName()));

        conversation.setIsView(true);
        conversation.setIsDelete(false);
        conversation.setIsActive(true);

        conversation = conversationRepository.save(conversation);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, conversation.getId())
                        .build()
        );
    }

    @Override
    public ActionDTO deleteConversationV2(DeleteParam d) {
        Conversation conversation = conversationRepository.findById(d.getId()).orElseThrow(
                ()->new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_CONVERSATION)
        );

//        Long id = SecurityUtil.getCurrentUserId();
//
//        User user = userRepository.findById(id).orElseThrow(
//
//                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
//                        Constant.RESPONSE.MESSAGE.C403_EDIT_CONVERSATION)
//        );

        conversation.setIsDelete(true);

        if (!messageRepository.findByConversation(conversation).isEmpty()) {
            messageRepository.deleteMessageByConversationId(d.getId());
        }

        conversationRepository.save(conversation);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, d.getId())
                        .build()
        );
    }

    @Override
    public ActionDTO deleteConversation(ConversationDeleteDTO con) {

        Conversation conversation = conversationRepository.findConByCode(con.getCode());
        if (conversation == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_CONVERSATION);
        }

        Long id = SecurityUtil.getCurrentUserId();

        User user = userRepository.findById(id).orElseThrow(

                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C403_EDIT_CONVERSATION)
        );

        conversation.setIsDelete(true);

        if (!messageRepository.findByConversation(conversation).isEmpty()) {
            messageRepository.deleteMessageByConversationId(con.getId());
        }

        conversationRepository.save(conversation);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, con.getId())
                        .build()
        );
    }

    @Override
    public ActionDTO updateUser(UpdateUserConversation u) {

        Conversation conversation = conversationRepository.findById(u.getId()).orElseThrow(
                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_CONVERSATION,
                        u.getId())
        );

//        UserAuditModel user = SecurityUtil.getUserAuditModel();
//        if (conversation.getCreatedByUserID()!= user.getId()){
//            throw new GeneralException(Constant.RESPONSE.CODE.C403,
//                    Constant.RESPONSE.MESSAGE.C403_EDIT_CONVERSATION,
//                    user.getId);
//        }

        List<User> users = new ArrayList<>();
        for (Long i : u.getIds()) {
            users.add(
                    userRepository.findById(i).orElseThrow(
                            () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                                    Constant.RESPONSE.MESSAGE.C404_USER,
                                    i)
                    )
            );
        }

        List<User> menbers = conversation.getUsers();


        if (u.getFlag() == true) {

            menbers.addAll(users);

        } else {

            if (Boolean.FALSE.equals(menbers.containsAll(users))) {
                throw new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_INVALID_MEMVBER
                );
            }
            menbers.removeAll(users);
        }

        conversation.setUsers(menbers);

        conversationRepository.save(conversation);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, u.getId())
                        .build()
        );
    }


    private String makeCode(String name) {
        return Base64.getUrlEncoder().encodeToString(name.getBytes());
    }

}
