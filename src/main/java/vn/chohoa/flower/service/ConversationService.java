package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.GetListConversationParam;

public interface ConversationService {

    ActionDTO createMessage(MessageNewDTO message);

    ActionDTO updateContentMessage(MessageUpdateDTO message);

    ActionDTO deleteContentMessage(MessageDeleteDTO message);

    ActionDTO isViewMessage(MessageViewerDTO message);

    PageDTO findConversationByCode(GetListConversationParam p);

}
