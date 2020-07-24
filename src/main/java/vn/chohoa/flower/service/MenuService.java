package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.MenuItemNewDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;

public interface MenuService {
    ActionDTO createMenu(MenuItemNewDTO item);
    PageDTO getMenu(PageParam p);
}
