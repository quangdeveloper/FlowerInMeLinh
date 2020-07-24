package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.MenuItemDTO;
import vn.chohoa.flower.dto.MenuItemNewDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.MenuMapper;
import vn.chohoa.flower.model.Menu;
import vn.chohoa.flower.repository.MenuRepository;
import vn.chohoa.flower.service.MenuService;
import vn.chohoa.flower.util.Constant;

import java.util.List;

@Service
@Slf4j
public class MenuServiveImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ActionDTO createMenu(MenuItemNewDTO item) {

        Menu menu = menuRepository.findByName(item.getName());

        if (menu != null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409_MENU);
        }

        menu = menuMapper.toMenuFromMenuNewDTO(item);
        if (menu.getParentId() == null) menu.setParentId(0L);
        if (menu.getIsSystemPermission() == null) menu.setIsSystemPermission(true);
        if (menu.getClassStyle() == null) menu.setClassStyle(" ");
        if (menu.getPath() == null) menu.setPath(" ");

        Menu mS = menuRepository.save(menu);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, mS.getId())
                .build()
        );
    }

    @Override
    public PageDTO getMenu(PageParam p) {
        Pageable pageable = PageRequest.of(p.getPageNo() - 1,
                p.getPageSize(),
                Sort.by("parentId").ascending()
        );
        final Page<Menu> page = menuRepository.findAll(pageable);

        if (page.isEmpty() ){
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_MENU);
        }

        List<MenuItemDTO> items = page.map(menuMapper::toMenuItemDtoFromMEnuItem).getContent();
        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(items)
                .total(total)
                .build();
    }
}
