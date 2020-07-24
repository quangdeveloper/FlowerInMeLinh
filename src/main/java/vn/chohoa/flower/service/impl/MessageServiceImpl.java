package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.DeleteParam;
import vn.chohoa.flower.dto.apiParam.GetConversationParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.ConversationMapper;
import vn.chohoa.flower.mapper.MessageMapper;
import vn.chohoa.flower.model.Conversation;
import vn.chohoa.flower.model.Message;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.model.UserAuditModel;
import vn.chohoa.flower.repository.ConversationRepository;
import vn.chohoa.flower.repository.MessageRepository;
import vn.chohoa.flower.repository.MojiRepository;
import vn.chohoa.flower.repository.UserRepository;
import vn.chohoa.flower.service.MessageService;
import vn.chohoa.flower.util.Constant;
import vn.chohoa.flower.util.SecurityUtil;

import java.security.Security;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MojiRepository mojiRepository;

    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private MessageMapper messageMapper;


    @Autowired
    private UserRepository userRepository;


    @Override
    public ActionDTO createMessage(MessageNewDTO message) {

        if (conversationRepository.findByID(message.getId()) == null) {

            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_CONVERSATION);
        }

        if (message.getMoji() != null)
            mojiRepository.findById(message.getMoji()).orElseThrow(
                    () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                            Constant.RESPONSE.MESSAGE.C404_MOJI)
            );

        if (message.getMoji() == null && message.getContent() == null && message.getFileContent() == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C400,
                    Constant.RESPONSE.MESSAGE.C400_DATA);
        }

        Message mess = messageMapper.toMessageFromMessageNewDTO(message);

        mess.setMoji(mojiRepository.findByID(message.getMoji()));

        mess.setConversation(conversationRepository.findByID(message.getId()));

        mess.setIsSpam(false);

        mess.setIsView(false);

        mess.setIsSend(true);

        mess.setIsDelete(false);

        mess = messageRepository.save(mess);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, mess.getId())
                        .build()
        );

    }

    @Override
    public PageDTO findConversationByCode(GetConversationParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo() - 1, p.getPageSize(),
                Sort.by("id").descending());

        Conversation con = conversationRepository.findById(p.getId()).orElseThrow(
                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_CONVERSATION2)
        );

        if (Boolean.TRUE.equals(con.getIsDelete())) {
            throw new GeneralException(Constant.RESPONSE.CODE.C802,
                    Constant.RESPONSE.MESSAGE.C802_CONVERSATION_CANCEL);
        }

//        UserAuditModel user = SecurityUtil.getUserAuditModel();
//        if (!con.getUsers().contains(user)){
//            throw new GeneralException(Constant.RESPONSE.CODE.C403,
//                    Constant.RESPONSE.MESSAGE.C403_EDIT_CONVERSATION,
//                    user.getId());
//        }

        final Page<Message> page = messageRepository.findByConversation(pageable, con);

        List<MessageDTO> list = page.map(messageMapper::toMessageDTOFromMessage).getContent();

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

    @Override
    public ActionDTO updateContentMessage(MessageUpdateDTO message) {

        Message messOld = messageRepository.findById(message.getId()).orElseThrow(
                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_MESSAGE)
        );

//        UserAuditModel user = SecurityUtil.getUserAuditModel();
////
////        if (messOld.getCreatedByUserID() != user.getId() || !messOld.getCreatedByUsername().equals(user.getUsername())) {
////            throw new GeneralException(Constant.RESPONSE.CODE.C403,
////                    Constant.RESPONSE.MESSAGE.C403_EDIT_MESSAGE);
////        }

//        Message mess = messageMapper.toMessageFromMessageUpdateDTO(message);
//
//        mess.setIsDelete(false);
//
//        mess.setIsActive(true);
//
//        mess.setIsSend(true);
//
//        mess.setIsView(false);
//
//        mess.setMoji(messOld.getMoji());
//
//        mess.setFileContent(messOld.getFileContent());
//
//        mess.setContent(message.getContent());

//        mess = messageRepository.save(mess);

        messOld.setContent(message.getContent());

        messageRepository.save(messOld);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, messOld.getId())
                .build());
    }

    @Override
    public ActionDTO deleteContentMessage(MessageDeleteDTO message) {

        Conversation con = conversationRepository.findConByCode(message.getCode());
        if (con == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_CONVERSATION);
        }

        Message mess = messageRepository.findById(message.getId()).orElseThrow(
                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_MESSAGE)
        );

        UserAuditModel user = SecurityUtil.getUserAuditModel();

        if (mess.getCreatedByUserID() != user.getId() || !mess.getCreatedByUsername().equals(user.getUsername())) {
            throw new GeneralException(Constant.RESPONSE.CODE.C403,
                    Constant.RESPONSE.MESSAGE.C403_EDIT_MESSAGE);
        }

        mess.setIsDelete(true);

        messageRepository.save(mess);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE,mess.getId())
                .build());

    }

    @Override
    public ActionDTO deleteMessage(DeleteParam p) {


        Message mess = messageRepository.findById(p.getId()).orElseThrow(
                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_MESSAGE)
        );

//        UserAuditModel user = SecurityUtil.getUserAuditModel();
//
//        if (mess.getCreatedByUserID() != user.getId() || !mess.getCreatedByUsername().equals(user.getUsername())) {
//            throw new GeneralException(Constant.RESPONSE.CODE.C403,
//                    Constant.RESPONSE.MESSAGE.C403_EDIT_MESSAGE);
//        }

        mess.setIsDelete(true);

        messageRepository.save(mess);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, mess.getId())
                .build());
    }

    @Override
    public ActionDTO isViewMessage(MessageViewerDTO message) {


        Message mess = messageRepository.findById(message.getId()).orElseThrow(
                () -> new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_MESSAGE)
        );

        if (mess.getIsDelete() == false) throw new GeneralException(Constant.RESPONSE.CODE.C809,
                Constant.RESPONSE.MESSAGE.C809);

        UserAuditModel user = SecurityUtil.getUserAuditModel();

        if (mess.getCreatedByUserID() != user.getId() && !mess.getCreatedByUsername().equals(user.getUsername())) {
            mess.setIsView(true);
            messageRepository.save(mess);
        }

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, message.getId())
                .build());
    }


}
