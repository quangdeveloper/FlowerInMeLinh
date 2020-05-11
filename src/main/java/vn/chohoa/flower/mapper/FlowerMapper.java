package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.FlowerDTO;
import vn.chohoa.flower.dto.FlowerNewDTO;
import vn.chohoa.flower.model.Flower;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlowerMapper {

    @Mappings({
            @Mapping(source = "flowerCategory.id",target = "type"),
            @Mapping(source = "farm.address",target = "address"),
            @Mapping(source = "farm.name",target = "nameFarm")
    })
    FlowerDTO toFlowerDTOFromFlower(Flower f);

    List<FlowerDTO> toFlowerDTOFromFlowers(List<Flower> f);

    Flower toFlowerFromFlowerDTO(FlowerDTO f);

    Flower toFlowerFromFlowerNewDTO(FlowerNewDTO f);



}
