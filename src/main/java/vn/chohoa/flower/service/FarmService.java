package vn.chohoa.flower.service;

import org.springframework.data.domain.Pageable;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.FarmDTO;
import vn.chohoa.flower.dto.FarmNewDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.GetListFarmParam;
import vn.chohoa.flower.dto.apiParam.PageParam;

public interface FarmService {

    PageDTO findAll(PageParam p);

    FarmDTO findByID(Long id);

    PageDTO findByNameAndOwnerAndIsActive(GetListFarmParam p);

    ActionDTO insertFarm(FarmNewDTO farmDTO);

    PageDTO findByIsActiveTrue(PageParam p);

    PageDTO findByIsActiveFalse(PageParam p);



}
