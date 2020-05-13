package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.chohoa.flower.dto.UserConversationDTO;
import vn.chohoa.flower.dto.UserDTO;
import vn.chohoa.flower.dto.UserNewDTO;
import vn.chohoa.flower.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "person",source = "personNewDTO")
    })
    User toUserFromUserNewDTO(UserNewDTO u);


    @Mappings({
            @Mapping(target = "fullName",source = "person.fullName"),
            @Mapping(target = "phone",source = "person.phone"),
            @Mapping(target = "email",source = "person.email"),
            @Mapping(target = "birthday",source = "person.birthday")
    })
    UserDTO toUserDTOFromUser(User u);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "userName", source = "userName"),
            @Mapping(target = "fullName", source = "person.fullName")
    })
    UserConversationDTO toUserConversationDtoFromUser(User u);

    @Named("mapUserConWithUser")
    List<UserConversationDTO> toUserConversationDtoFromUsers(List<User> u);

}
