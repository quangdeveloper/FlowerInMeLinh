package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.ExcelDTO;

import java.util.List;

public interface ExcelService {

    byte[] write(ExcelDTO excelDTO, List<Object[]> rows);
}
