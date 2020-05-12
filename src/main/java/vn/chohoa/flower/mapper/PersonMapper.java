package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.PersonNewDTO;
import vn.chohoa.flower.dto.UserDTO;
import vn.chohoa.flower.dto.UserNewDTO;
import vn.chohoa.flower.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPersonFromPersonNewDTO(PersonNewDTO person);


//    @Mappings({
//            @Mapping(target = "id",source = "")
//    })
//    Person toPersonFromUserNewDTO(UserNewDTO userNewDTO);
}
