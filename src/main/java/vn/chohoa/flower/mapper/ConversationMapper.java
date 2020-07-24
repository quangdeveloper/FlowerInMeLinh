package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.model.Conversation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {


    @Mappings({
            @Mapping(source = "userIds",target = "users",ignore = true)
    })
    Conversation toConversationFromConversationNewDTO(ConversationNewDTO newDTO);


    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name")
    })
    ConversationDTO toConversationDtoFromConversation(Conversation con);
    List<ConversationDTO> toConversationDtoFromConversation(List<Conversation> con);
}
