package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.MojiMapper;
import vn.chohoa.flower.model.Moji;
import vn.chohoa.flower.repository.MojiRepository;
import vn.chohoa.flower.service.MojiService;
import vn.chohoa.flower.util.Constant;

import java.util.List;
import java.util.Random;

@Service
public class MojiServicesImpl implements MojiService {

    @Autowired
    private MojiRepository mojiRepository;

    @Autowired
    private MojiMapper mojiMapper;

    @Override
    public PageDTO findAll(PageParam p) {

        Pageable pageable = PageRequest.of(p.getPageNo() - 1, p.getPageSize(), Sort.by("id").descending());

        final Page<Moji> page = mojiRepository.findAll(pageable);

        List<MojiDTO> list = page.map(mojiMapper::toMojiDtoFromMoji).getContent();

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .total(total)
                .content(list)
                .build();
    }

    @Override
    public ActionDTO createMoji(MojiNewDTO moji) {

        if (mojiRepository.findByLinkMoji(moji.getLinkMoji()) != null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409_MOJI_LINK);
        }

        if (mojiRepository.findByName(moji.getName()) != null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409_MOJI_NAME);
        }
        Moji old = mojiRepository.findByGroupId(moji.getGroupId());
        if (old !=null){
            if (moji.getGroupName() != old.getGroupName()){
                throw new GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_GROUP_MOJI);
            }
        }

        Moji newMoji = mojiMapper.toMojiFromMojiNewDTO(moji);

        newMoji.setCode(makeCode(newMoji.getName(), newMoji.getGroupName()));

        newMoji = mojiRepository.save(newMoji);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, newMoji.getId())
                .build());
    }

    @Override
    public ActionDTO updateMoji(MojiUpdateDTO m) {

        Moji oldMoji = mojiRepository.findByIdAndNameAndCode(m.getId(),m.getName(),m.getCode());
        if (oldMoji == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_MOJI);
        }

        Moji moji = mojiMapper.toMojiFromMojiUpdateDTO(m);

        mojiRepository.save(moji);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, m.getId())
                .build());


    }

    @Override
    public ActionDTO deleteMoji(MojiDeleteDTO m) {

        Moji moji = mojiRepository.findByIdAndNameAndCode(m.getId(), m.getName(), m.getCode());
        if (moji == null) {
            throw new GeneralException(Constant.RESPONSE.CODE.C404,
                    Constant.RESPONSE.MESSAGE.C404_MOJI);
        }
        moji.setIsActive(false);

        mojiRepository.save(moji);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, m.getId())
                .build());
    }

    private String makeCode(String name, String groupName) {

        StringBuilder stringBuilder = new StringBuilder();

        String[] names = name.split(" ");

        for (String i : names) {

            stringBuilder.append(i.substring(0, 1));
        }

        stringBuilder.append(".");
        stringBuilder.append(groupName.substring(0, 3));
        stringBuilder.append(".");
        Random rd = new Random(15001);
        Integer intRd = rd.nextInt();
        stringBuilder.append(intRd.toString());

        return stringBuilder.toString().toUpperCase();

    }
}
