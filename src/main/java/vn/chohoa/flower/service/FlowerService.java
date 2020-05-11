package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.FlowerDTO;
import vn.chohoa.flower.dto.FlowerNewDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;

public interface FlowerService {

    PageDTO findAll(PageParam p);

    PageDTO findByIsSale(PageParam p,Boolean isSale);

    ActionDTO createFlower(FlowerNewDTO f);

    ActionDTO updateFlower(FlowerNewDTO f);
}
