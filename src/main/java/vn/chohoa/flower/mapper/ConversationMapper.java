package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.ConversationDTO;
import vn.chohoa.flower.dto.MessageDeleteDTO;
import vn.chohoa.flower.dto.MessageNewDTO;
import vn.chohoa.flower.dto.MessageUpdateDTO;
import vn.chohoa.flower.model.Conversation;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

    @Mappings({
            @Mapping(target = "senderId", source = "senderId"),
            @Mapping(target = "senderUsername", source = "senderUsername"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "fileContent", source = "contentFile"),
            @Mapping(target = "moji", source = "idMoji", ignore = true),
            @Mapping(target = "users", source = "userIds", ignore = true)
    })
    Conversation toConversationFromMessageNewDTO(MessageNewDTO mess);


    @Mappings({
            @Mapping(source = "users", target = "users", qualifiedByName = "mapUserConWithUser")
    })
    ConversationDTO toConversationDtoFromConversation(Conversation con);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "code", source = "code"),
            @Mapping(target = "senderId", source = "editorId"),
            @Mapping(target = "senderUsername", source = "editorUsername"),
            @Mapping(target = "content", source = "content"),
    })
    Conversation toConversationFromUpdateMessageDTO(MessageUpdateDTO mess);


}
