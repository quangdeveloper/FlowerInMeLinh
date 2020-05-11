package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.FlowerCategoryDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.DataEmptyException;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.exception.NotFoundException;
import vn.chohoa.flower.mapper.FlowerCategoryMapper;
import vn.chohoa.flower.model.FlowerCategory;
import vn.chohoa.flower.repository.FlowerCategoryRepository;
import vn.chohoa.flower.service.FlowerCatgoryService;
import vn.chohoa.flower.util.Constant;

import java.util.List;

@Service
public class FlowerCatgoryServiceImpl implements FlowerCatgoryService {

    @Autowired
    private FlowerCategoryRepository flowerCategoryRepository;

    @Autowired
    FlowerCategoryMapper flowerCategoryMapper;

    @Override
    public PageDTO getAllFlowerCategory(PageParam p) {

        Pageable page = PageRequest.of(
                p.getPageNo() - 1,
                p.getPageSize(),
                Sort.by("id").descending()
        );

        final Page<FlowerCategory> listFC = flowerCategoryRepository.findAll(page);

        List<FlowerCategoryDTO> cates = listFC.map(flowerCategoryMapper::toFlowerCategoryDTOFromCategory)
                .getContent();

        final long total = listFC.getTotalElements();
        return PageDTO.builder()
                .content(cates)
                .total(total)
                .build();
    }


    @Override
    public FlowerCategoryDTO findById(Long id) {

        final FlowerCategory flowerCategory = flowerCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_FLOWER_CATEGORY));

        final FlowerCategoryDTO flower = flowerCategoryMapper.toFlowerCategoryDTOFromCategory(flowerCategory);
        return flower;
    }


    @Override
    public ActionDTO insertCate(FlowerCategoryDTO s) {

        if (flowerCategoryRepository.findByCode(s.getCode()) != null) {

            throw new GeneralException(
                    Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409,
                    ImmutableMap.builder()
                            .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, 0)
                            .build()
            );
        }

        FlowerCategory flowerCategory = flowerCategoryMapper.
                toFlowerCategoryFromFlowerCategoryDTO(s);

        flowerCategory = flowerCategoryRepository.save(flowerCategory);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, flowerCategory.getId())
                .build()
        );
    }

    @Override
    public ActionDTO updateCategoryFlower(FlowerCategoryDTO s) {

        if (!flowerCategoryRepository.existsById(s.getId())) {
            throw new GeneralException(
                    Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404,
                    new ActionDTO(
                            ImmutableMap.builder()
                                    .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, 0)
                                    .build())
            );
        }
        FlowerCategory flowerCategory = flowerCategoryMapper.toFlowerCategoryFromFlowerCategoryDTO(s);

        flowerCategory = flowerCategoryRepository.save(flowerCategory);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, flowerCategory.getId())
                .build()
        );
    }

    @Override
    public ActionDTO delete(Long id) {

        if (!flowerCategoryRepository.existsById(id)) {
            throw new NotFoundException(Constant.RESPONSE.CODE.C404,Constant.RESPONSE.MESSAGE.C404_DELETE);
        }

        flowerCategoryRepository.deleteById(id);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, id)
                .build());
    }
}
