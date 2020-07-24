package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import vn.chohoa.flower.dto.MenuItemDTO;
import vn.chohoa.flower.dto.MenuItemNewDTO;
import vn.chohoa.flower.model.Menu;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    Menu toMenuFromMenuNewDTO(MenuItemNewDTO menu);
    MenuItemDTO toMenuItemDtoFromMEnuItem(Menu menuItem);
}
