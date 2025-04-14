package com.utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelUtils {

    public static List<HashMap<String, String>> getExcelData(String filePath, String sheetName) {
        List<HashMap<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell runCell = row.getCell(colCount - 1);
                String runFlag = runCell != null ? runCell.getStringCellValue() : "N";

                if (runFlag.equalsIgnoreCase("Y")) {
                    HashMap<String, String> rowData = new HashMap<>();
                    for (int j = 0; j < colCount; j++) {
                        Cell cell = row.getCell(j);
                        String key = headerRow.getCell(j).getStringCellValue();
                        String value = getCellValue(cell).toString();
                        rowData.put(key, value);
                    }
                    dataList.add(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue()
                    : cell.getNumericCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
