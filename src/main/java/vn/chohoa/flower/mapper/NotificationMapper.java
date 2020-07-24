package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.NotificationNewDTO;
import vn.chohoa.flower.dto.NotificationUpdateDTO;
import vn.chohoa.flower.model.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {


    Notification toNotificationFromNotificationNewDTO(NotificationNewDTO n);

    Notification toNotificationFromNotificationUpdateDTO(NotificationUpdateDTO n);

}
