package vn.chohoa.flower.service;

import org.springframework.data.domain.Page;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;

public interface MojiService {

    PageDTO findAll(PageParam p);


}
