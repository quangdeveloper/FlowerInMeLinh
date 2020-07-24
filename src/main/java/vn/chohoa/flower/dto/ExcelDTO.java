package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class ExcelDTO {

    private String fileName;

    private String sheetName;

    @Builder.Default
    private List<String> columnTitles = Collections.emptyList();

    @Builder.Default
    private List<Integer> columnWidth = Collections.emptyList();
}
