package vn.chohoa.flower.service;

import org.springframework.data.domain.jaxb.SpringDataJaxb;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.FlowerCategoryDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.model.FlowerCategory;

import java.util.List;

public interface FlowerCatgoryService {

    PageDTO getAllFlowerCategory(PageParam p);

    FlowerCategoryDTO findById(Long id);

    ActionDTO insertCate(FlowerCategoryDTO s);

    ActionDTO updateCategoryFlower(FlowerCategoryDTO s);

    ActionDTO delete(Long id);
}
