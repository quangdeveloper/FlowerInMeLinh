package vn.chohoa.flower.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.model.AttackFileMail;
import vn.chohoa.flower.repository.AttackFileEmailRepository;
import vn.chohoa.flower.service.AttackFileMailService;
@Service
public class AttackFileMailServiceImpl implements AttackFileMailService {
    @Autowired
    private AttackFileEmailRepository repository;

    @Override
    public AttackFileMail save(AttackFileMail att) {
        return repository.save(att);
    }
}
