package vn.chohoa.flower.service;

import vn.chohoa.flower.model.Keyword;

import java.util.List;

public interface KeywordService {

    Keyword createNewKeyword(Keyword k);

    Iterable<Keyword> createNewKeywords(List<Keyword> keywords);


}
