package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.*;

import vn.chohoa.flower.dto.apiParam.DeleteParam;
import vn.chohoa.flower.dto.apiParam.GetConversationParam;

public interface MessageService {



    ActionDTO createMessage(MessageNewDTO message);

    ActionDTO updateContentMessage(MessageUpdateDTO message);
//
    ActionDTO deleteContentMessage(MessageDeleteDTO message);
//
    ActionDTO isViewMessage(MessageViewerDTO message);
//
    PageDTO findConversationByCode(GetConversationParam p);

    ActionDTO deleteMessage(DeleteParam p);

}
