package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.NotificationNewDTO;
import vn.chohoa.flower.dto.NotificationUpdateDTO;
import vn.chohoa.flower.mapper.NotificationMapper;
import vn.chohoa.flower.model.Notification;
import vn.chohoa.flower.repository.NotificationRepository;
import vn.chohoa.flower.service.NotificationService;
import vn.chohoa.flower.util.Constant;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public ActionDTO insert(NotificationNewDTO p) {

        Notification no = notificationMapper.toNotificationFromNotificationNewDTO(p);
        return new ActionDTO(ImmutableMap.builder()
        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE,notificationRepository.save(no))
        .build());
    }

    @Override
    public ActionDTO update(NotificationUpdateDTO p) {

        Notification no = notificationMapper.toNotificationFromNotificationUpdateDTO(p);
        no.setId(p.getId());
        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE,notificationRepository.save(no))
                .build());
    }
}
