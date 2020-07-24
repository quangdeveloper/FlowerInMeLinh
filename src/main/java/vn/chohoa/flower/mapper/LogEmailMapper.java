package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.EmailNewDTO;
import vn.chohoa.flower.model.LogEmail;

@Mapper(componentModel = "spring")
public interface LogEmailMapper {


    LogEmail toLogEmailFromEmailNewDTO(EmailNewDTO e);

}
