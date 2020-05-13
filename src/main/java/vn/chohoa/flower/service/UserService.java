package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.UserNewDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.model.User;

public interface UserService {

    ActionDTO createUser(UserNewDTO u);

    PageDTO getListUser(PageParam p);

    PageDTO getListUserConversationDTO(PageParam p);

    User updateToken(String username, String partner, String token);
}
