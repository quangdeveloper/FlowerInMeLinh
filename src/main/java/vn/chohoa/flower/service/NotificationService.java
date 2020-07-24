package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.NotificationNewDTO;
import vn.chohoa.flower.dto.NotificationUpdateDTO;

public interface NotificationService {

    ActionDTO insert(NotificationNewDTO p);
    ActionDTO update(NotificationUpdateDTO p);
}
