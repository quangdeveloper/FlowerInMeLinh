package vn.chohoa.flower.mapper;


import org.mapstruct.Mapper;
import vn.chohoa.flower.dto.FlowerCategoryDTO;
import vn.chohoa.flower.model.Flower;
import vn.chohoa.flower.model.FlowerCategory;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BooleanMapper.class})
public interface FlowerCategoryMapper {
    FlowerCategory toFlowerCategoryFromFlowerCategoryDTO(FlowerCategoryDTO flowerCategoryDTO);
    FlowerCategoryDTO toFlowerCategoryDTOFromCategory(FlowerCategory flowerCategory);
    List<FlowerCategoryDTO> toFlowerCategoryDTOsFromCategories(List<FlowerCategory> flowerCategory);

}
