package com.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReportUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    private static String filePath = "E:\\Shivam\\Downloads\\ExecutionReport.xlsx";
    private static int rowCount = 0;
    private static List<String> headers = new ArrayList<>();
    private static Map<String, Integer> headerIndexMap = new HashMap<>();

    static {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("ExecutionReport");
    }

    // Method to set custom headers dynamically
    public static void setHeaders(List<String> customHeaders) {
        headers = customHeaders;
        Row headerRow = sheet.createRow(rowCount++);

        // Style for header
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headerStyle);
            headerIndexMap.put(headers.get(i), i); // store index for mapping
        }
    }

    // Method to write a row of data using a Map
    public static void writeRow(Map<String, String> rowData) {
        Row row = sheet.createRow(rowCount++);

        String resultValue = rowData.getOrDefault("Result", "").toUpperCase();

        // Styles for pass/fail
        CellStyle passStyle = workbook.createCellStyle();
        passStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle failStyle = workbook.createCellStyle();
        failStyle.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle defaultStyle = "PASS".equals(resultValue) ? passStyle :
                                 "FAIL".equals(resultValue) ? failStyle : workbook.createCellStyle();

        for (String header : headers) {
            int cellIndex = headerIndexMap.get(header);
            Cell cell = row.createCell(cellIndex);
            cell.setCellValue(rowData.getOrDefault(header, ""));
            cell.setCellStyle(defaultStyle);
        }
    }

    // Save the Excel file
    public static void saveReport() {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            for (int i = 0; i < headers.size(); i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
