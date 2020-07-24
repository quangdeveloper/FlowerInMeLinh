package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import com.opensymphony.module.sitemesh.filter.PageResponseWrapper;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.FarmDTO;
import vn.chohoa.flower.dto.FarmNewDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.GetListFarmParam;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.exception.NotFoundException;
import vn.chohoa.flower.mapper.FarmMapper;
import vn.chohoa.flower.model.Farm;
import vn.chohoa.flower.repository.FarmRepository;
import vn.chohoa.flower.service.FarmService;
import vn.chohoa.flower.util.Constant;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmMapper farmMapper;


    @Override
    public PageDTO findAll(PageParam p) {

        Pageable pageable = PageRequest.of(
                p.getPageNo() - 1,
                p.getPageSize(),
                Sort.by("id").descending());

        final Page<Farm> page = farmRepository.findAll(pageable);

        List<FarmDTO> farmDTOs = page.map(farmMapper::toFromDTOFromFarm).getContent();

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(farmDTOs)
                .total(total)
                .build();
    }

    @Override
    public FarmDTO findByID(Long id) {

        Farm farm = farmRepository.findById(id).orElse(null);

        if (farm == null) throw new NotFoundException(Constant.RESPONSE.CODE.C404,

                Constant.RESPONSE.MESSAGE.C404_FARM);


        return farmMapper.toFromDTOFromFarm(farm);

    }

    @Override
    public PageDTO findByNameAndOwnerAndIsActive(GetListFarmParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo() - 1, p.getPageSize() - 1, Sort.by("id").descending());

        Page<Farm> farmPage = farmRepository.findByNameAndOwnerAndAddressAndIsActive(
                pageable,
                p.getName(),
                p.getOwner(),
                p.getAddress(),
                p.getIsActive()
        );

        final List<FarmDTO> farmDTOS = farmPage.map(farmMapper::toFromDTOFromFarm).getContent();

        final long total = farmPage.getTotalElements();

        return PageDTO.builder()
                .content(farmDTOS)
                .total(total)
                .build();
    }


    public ActionDTO insertFarm(FarmNewDTO farmDTO) {

        Farm farm = farmMapper.toFarmFromFarmNewDTO(farmDTO);

        farm.setCode(AutoCode(LocalDateTime.now().toString()));

        farm = farmRepository.save(farm);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, farm.getId())
                        .build()
        );
    }

    public ActionDTO updateFarm(FarmDTO f) {

        Farm fa = farmRepository.findById(f.getId()).orElseThrow(
                () -> new GeneralException(
                        Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_FARM,
                        ImmutableMap.builder()
                                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, 0)
                                .build()
                )
        );

        Farm farm = farmMapper.toFarmFromFarmDTO(f);

        farm = farmRepository.save(farm);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, farm.getId()).build());
    }

    public ActionDTO deleteFarm(Long id) {

        Farm f = farmRepository.findById(id).orElse(null);

        if (f == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_FARM,
                    ImmutableMap.builder()
                            .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, 0)
                            .build());
        }
        f.setIsActive(false);
        farmRepository.save(f);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, id)
                .build());
    }

    @Override
    public PageDTO findByIsActiveTrue(PageParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo()-1,p.getPageSize(),Sort.by("id").descending());

        Page<Farm> farmPage = farmRepository.findByIsAcgtiveTrue(pageable);

        final List<FarmDTO> list =  farmPage.map(farmMapper::toFromDTOFromFarm).getContent();

        final long total = farmPage.getTotalElements();

        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

    @Override
    public PageDTO findByIsActiveFalse(PageParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo()-1,p.getPageSize(),Sort.by("id").descending());

        Page<Farm> page = farmRepository.findByIsAcgtiveFalse(pageable);

        final List<FarmDTO> list = page.map(farmMapper::toFromDTOFromFarm).getContent();

        final long total = page.getTotalElements();


        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

    private String AutoCode(String str){

        String code = "FARM_"+Base64.getUrlEncoder().encodeToString(str.getBytes());
        return code;
    }

}
