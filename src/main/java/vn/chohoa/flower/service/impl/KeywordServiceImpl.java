package vn.chohoa.flower.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.model.Keyword;
import vn.chohoa.flower.repository.KeywordRepository;
import vn.chohoa.flower.service.KeywordService;

import java.util.Iterator;
import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public Keyword createNewKeyword(Keyword k) {

        final Keyword keyword =keywordRepository.save(k);

        return keyword;
    }

    @Override
    public Iterable<Keyword> createNewKeywords(List<Keyword> keywords) {

        final  Iterable<Keyword> iterator = keywords;

        return keywordRepository.saveAll(iterator);
    }

}
