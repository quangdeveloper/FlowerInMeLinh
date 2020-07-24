package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.FlowerDTO;
import vn.chohoa.flower.dto.FlowerNewDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.DataExistsException;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.FlowerMapper;
import vn.chohoa.flower.model.Farm;
import vn.chohoa.flower.model.Flower;
import vn.chohoa.flower.model.FlowerCategory;
import vn.chohoa.flower.repository.FarmRepository;
import vn.chohoa.flower.repository.FlowerCategoryRepository;
import vn.chohoa.flower.repository.FlowerRepository;
import vn.chohoa.flower.service.FlowerService;
import vn.chohoa.flower.util.Constant;

import java.util.List;


@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private FlowerMapper flowerMapper;

    @Autowired
    private FlowerCategoryRepository flowerCategoryRepository;


    @Autowired
    private FarmRepository farmRepository;

    @Override
    public PageDTO findAll(PageParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo()-1,p.getPageSize(), Sort.by("id").descending());

        final  Page<Flower> flowerPage = flowerRepository.findAll(pageable);

        List<FlowerDTO> page = flowerPage.map(flowerMapper::toFlowerDTOFromFlower).getContent();

        final long total = flowerPage.getTotalElements();

        return PageDTO.builder()
                .content(page)
                .total(total)
                .build();
    }

    @Override
    public ActionDTO createFlower(FlowerNewDTO f) {

        Flower flower = flowerMapper.toFlowerFromFlowerNewDTO(f);

        Farm farm = farmRepository.findById(f.getFarmId()).orElseThrow(
                ()-> new GeneralException(
                        Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_FARM
                )
        );

        flower.setFarm(farm);

        FlowerCategory cate = flowerCategoryRepository.findById(f.getCateId()).orElseThrow(
                ()->new GeneralException(
                        Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_FLOWER_CATEGORY
                )
        );

        flower.setFlowerCategory(cate);

        flower.setIsSale(false);

        flower.setIsActive(true);

        flower = flowerRepository.save(flower);

        return new ActionDTO(
                ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, flower.getId())
                .build()
        );
    }

    @Override
    public ActionDTO updateFlower(FlowerNewDTO f) {

        Flower s = flowerRepository.findById(f.getId()).orElse(null);

        if (s == null) throw  new GeneralException(
                Constant.RESPONSE.CODE.C404,
                Constant.RESPONSE.MESSAGE.C404_FLOWER,
               new ActionDTO(ImmutableMap.builder()
               .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE,0).build())
        );

        Flower flower = flowerMapper.toFlowerFromFlowerNewDTO(f);

        Farm farm = farmRepository.findById(f.getFarmId()).orElseThrow(
                ()-> new GeneralException(
                        Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_FARM
                )
        );

        flower.setFarm(farm);

        FlowerCategory cate = flowerCategoryRepository.findById(f.getCateId()).orElseThrow(
                ()->new GeneralException(
                        Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_FLOWER_CATEGORY
                )
        );

        flower.setFlowerCategory(cate);

        flower = flowerRepository.save(flower);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, flower.getId())
                        .build()
        );
    }

    @Override
    public PageDTO findByIsSale(PageParam p, Boolean isSale) {

        Pageable pageable = PageRequest.of(p.getPageNo()-1,p.getPageSize(),Sort.by("id").descending());
        final Page<Flower> page;
        if(isSale){

             page = flowerRepository.findByIsSaleTrue(pageable);
        }else {

            page = flowerRepository.findByIsSaleFalse(pageable);
        }

//         List<FlowerDTO> list = page.map(flowerMapper::toFlowerDTOFromFlower).getContent();

        List<FlowerDTO> list = flowerMapper.toFlowerDTOFromFlowers(page.getContent());

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

}
