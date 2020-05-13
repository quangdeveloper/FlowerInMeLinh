package vn.chohoa.flower.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.repository.MojiRepository;
import vn.chohoa.flower.service.MojiService;

@Service
public class MojiServicesImpl implements MojiService {

    @Autowired
    private MojiRepository mojiRepository;


    @Override
    public PageDTO findAll(PageParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo() - 1, p.getPageSize());


        return null;
    }
}
