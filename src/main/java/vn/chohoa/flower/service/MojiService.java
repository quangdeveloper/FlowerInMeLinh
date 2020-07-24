package vn.chohoa.flower.service;

import org.springframework.data.domain.Page;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.PageParam;

public interface MojiService {

    PageDTO findAll(PageParam p);

    ActionDTO createMoji(MojiNewDTO mojiNewDTO);

    ActionDTO updateMoji(MojiUpdateDTO m);

    ActionDTO deleteMoji(MojiDeleteDTO m);

}
