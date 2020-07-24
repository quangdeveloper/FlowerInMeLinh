package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.chohoa.flower.dto.MojiDTO;
import vn.chohoa.flower.dto.MojiNewDTO;
import vn.chohoa.flower.dto.MojiUpdateDTO;
import vn.chohoa.flower.model.Moji;

@Mapper(componentModel = "spring")
public interface MojiMapper {

    MojiDTO toMojiDtoFromMoji(Moji moji);


    Moji toMojiFromMojiNewDTO(MojiNewDTO mojiNewDTO);

    Moji toMojiFromMojiUpdateDTO(MojiUpdateDTO moji);
}
