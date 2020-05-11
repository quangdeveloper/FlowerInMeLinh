package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.springframework.web.bind.annotation.Mapping;
import vn.chohoa.flower.dto.FarmDTO;
import vn.chohoa.flower.model.Farm;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    FarmDTO toFromDTOFromFarm(Farm f);
    Farm toFarmFromFarmDTO(FarmDTO f);


}
