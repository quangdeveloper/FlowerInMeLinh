package vn.chohoa.flower.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.chohoa.flower.dto.ExcelDTO;
import vn.chohoa.flower.service.ExcelService;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public byte[] write(ExcelDTO excelDTO, List<Object[]> rows) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(excelDTO.getSheetName());

        Row title = sheet.createRow(0);
        for (int i = 0; i < excelDTO.getColumnTitles().size(); i++) {
            Cell cell = title.createCell(i);
            cell.setCellStyle(getBoldCellStyle(sheet, true, 11, true));
            cell.setCellValue(excelDTO.getColumnTitles().get(i));
            sheet.setColumnWidth(i, excelDTO.getColumnWidth().get(i) * 256);

        }
        for (int i = 0; i < rows.size(); i++) {
            Row rowI = sheet.createRow(i + 1);

            Object[] objects = rows.get(i);
            for (int j = 0; j < objects.length; j++) {
                Cell cell = rowI.createCell(j);
                cell.setCellStyle(getBoldCellStyle(sheet, false, 11, true));
                cell.setCellValue(ObjectUtils.isEmpty(objects[j]) ? "" :String.valueOf(objects[j]).trim());
            }
        }

        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }catch (Exception ex){
            log.error("{}",ex);
        }
        return new byte[0];
    }

    private CellStyle getBoldCellStyle(Sheet sheet, Boolean bold, int fontSize, Boolean alignCentre) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(bold);
        font.setFontHeightInPoints((short) fontSize);
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(alignCentre ? HorizontalAlignment.CENTER : HorizontalAlignment.LEFT);
        return cellStyle;

    }
}
