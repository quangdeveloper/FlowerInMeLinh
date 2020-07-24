package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.DeleteParam;
import vn.chohoa.flower.dto.apiParam.GetConversationParam;
import vn.chohoa.flower.dto.apiParam.PageParam;

import java.util.List;

public interface ConversationService {

    ActionDTO createConversation(ConversationNewDTO con);

    ActionDTO deleteConversation(ConversationDeleteDTO con);

    ActionDTO deleteConversationV2(DeleteParam d);

    ActionDTO updateUser(UpdateUserConversation u);

    PageDTO findAll(PageParam p);

    RsDTO findbyUserId(GetConversationParam p);



}
