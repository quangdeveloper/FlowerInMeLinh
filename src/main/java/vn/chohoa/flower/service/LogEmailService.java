package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.EmailNewDTO;

public interface LogEmailService {

    ActionDTO insertLogEmail(EmailNewDTO e);

    void changeStatus(Long id);

}
