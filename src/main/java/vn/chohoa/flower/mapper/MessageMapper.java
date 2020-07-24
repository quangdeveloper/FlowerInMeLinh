package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.MessageDTO;
import vn.chohoa.flower.dto.MessageNewDTO;
import vn.chohoa.flower.dto.MessageUpdateDTO;
import vn.chohoa.flower.model.Conversation;
import vn.chohoa.flower.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "moji", source = "moji",ignore = true)
    })
    Message toMessageFromMessageNewDTO(MessageNewDTO mess);


    @Mappings({
            @Mapping(target = "moji", source = "moji.id"),
            @Mapping(target = "code", source = "conversation.code"),
    })
    MessageDTO toMessageDTOFromMessage(Message mess);

    @Mappings({
    })
    Message toMessageFromMessageUpdateDTO(MessageUpdateDTO mess);

}
