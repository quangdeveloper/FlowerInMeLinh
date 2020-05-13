package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.GetListConversationParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.ConversationMapper;
import vn.chohoa.flower.model.Conversation;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.repository.ConversationRepository;
import vn.chohoa.flower.repository.MojiRepository;
import vn.chohoa.flower.repository.UserRepository;
import vn.chohoa.flower.service.ConversationService;
import vn.chohoa.flower.util.Constant;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MojiRepository mojiRepository;

    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ActionDTO createMessage(MessageNewDTO message) {

        User sender = userRepository.findByIdAndUserName(message.getSenderId(), message.getSenderUsername());

        if (sender == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_SENDER);
        }

        if (message.getUserIds() == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C800, Constant.RESPONSE.MESSAGE.C800_RECEIVER);
        }

        for (Long i : message.getUserIds()) {
            userRepository.findById(i).orElseThrow(
                    () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                            Constant.RESPONSE.MESSAGE.C404_RECEIVER)
            );
        }

        if (message.getIdMoji() != null) {

            mojiRepository.findById(message.getIdMoji()).orElseThrow(
                    () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                            Constant.RESPONSE.MESSAGE.C404_MOJI)
            );
        }

        if (message.getIdMoji() == null && message.getContent() == null && message.getContentFile() == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C400,
                    Constant.RESPONSE.MESSAGE.C400_DATA);
        }

        Conversation conversation = conversationMapper.toConversationFromMessageNewDTO(message);

        List<User> users = new ArrayList<>();
        for (Long i : message.getUserIds()) {
            users.add(userRepository.findByID(i));
        }
        conversation.setUsers(users);

        conversation.setCode(makeCode(message.getUserIds()));

        if (message.getIdMoji() != null) {
            conversation.setMoji(mojiRepository.findByID(message.getIdMoji()));
        }
        conversation.setIsSpam(false);

        conversation.setIsView(false);

        conversation.setIsSend(true);

        conversation.setIsDelete(false);

        conversation = conversationRepository.save(conversation);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, conversation.getId())
                        .build()
        );

    }

    @Override
    public PageDTO findConversationByCode(GetListConversationParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo() - 1, p.getPageSize(),
                Sort.by("id").descending());

        final Page<Conversation> page = conversationRepository.findByCode(pageable, p.getCode());

        List<ConversationDTO> list = page.map(conversationMapper::toConversationDtoFromConversation).getContent();

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

    @Override
    public ActionDTO updateContentMessage(MessageUpdateDTO message) {

        Conversation con = conversationRepository.findByCodeAndId(message.getId(), message.getCode());
        if (con == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404);
        }

        User user = userRepository.findByIdAndUserName(message.getEditorId(), message.getEditorUsername());
        if (user == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_EDIT_USER);
        }

        if (con.getSenderId() != message.getEditorId() || !con.getSenderUsername().equals(message.getEditorUsername())) {
            throw new GeneralException(Constant.RESPONSE.CODE.C403,
                    Constant.RESPONSE.MESSAGE.C403_EDIT_MESSAGE);
        }

        Conversation conNew = conversationMapper.toConversationFromUpdateMessageDTO(message);

        conNew.setIsDelete(false);

        conNew.setIsSend(true);

        conNew.setIsView(false);

        conNew.setUsers(con.getUsers());

        conNew.setReceivedTime(con.getReceivedTime());

        conNew.setSendedTime(con.getSendedTime());

        conNew.setSenderId(con.getSenderId());

        conNew.setSenderUsername(con.getSenderUsername());

        conNew.setMoji(con.getMoji());

        conNew.setFileContent(con.getFileContent());

        conNew = conversationRepository.save(conNew);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, conNew.getId())
                .build());
    }

    @Override
    public ActionDTO deleteContentMessage(MessageDeleteDTO message) {


        if (userRepository.findByIdAndUserName(message.getEditorId(), message.getEditorUsername()) == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_EDIT_USER);
        }
        Conversation con = conversationRepository.findByCodeAndId(message.getId(), message.getCode());
        if (con == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_CONVERSATION);
        }
        if (con.getSenderId() != message.getEditorId() || !con.getSenderUsername().equals(message.getEditorUsername())) {
            throw new GeneralException(Constant.RESPONSE.CODE.C403,
                    Constant.RESPONSE.MESSAGE.C403_EDIT_MESSAGE);
        }

        con.setIsDelete(true);

        con = conversationRepository.save(con);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, con.getId())
                .build());

    }

    @Override
    public ActionDTO isViewMessage(MessageViewerDTO message) {

        if (userRepository.findByIdAndUserName(message.getViewerId(), message.getViewerUsername()) == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_VIEW_USER);
        }

        Conversation con = conversationRepository.findByCodeAndId(message.getId(), message.getCode());
        if (con == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_CONVERSATION);
        }

        if (con.getIsDelete()) throw new GeneralException(Constant.RESPONSE.CODE.C809,
                Constant.RESPONSE.MESSAGE.C809);

        if (con.getSenderId() != message.getViewerId() && !con.getSenderUsername().equals(message.getViewerUsername())) {
            con.setIsView(true);
            con = conversationRepository.save(con);
        }

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, con.getId())
                .build());
    }

    private String makeCode(List<Long> ids) {

        StringBuilder str = new StringBuilder();

        for (Long i : ids) {
            str.append(i.toString().toUpperCase());
        }

        return str.toString();
    }


}
