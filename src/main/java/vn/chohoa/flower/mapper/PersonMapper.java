package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.PersonNewDTO;
import vn.chohoa.flower.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPersonFromPersonNewDTO(PersonNewDTO person);
}
