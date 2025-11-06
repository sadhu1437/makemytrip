package com.cognizant.makemytrip1.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelWriter {

    public static void writeResult(String sheetName, int rowNum, int colNum, String result) {
        try {
            FileInputStream fis = new FileInputStream("data/TestData1.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);

            Cell cell = row.getCell(colNum);
            if (cell == null) cell = row.createCell(colNum);

            cell.setCellValue(result);

            fis.close();
            FileOutputStream fos = new FileOutputStream("data/TestData1.xlsx");
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (Exception e) {
            System.out.println("Excel write error: " + e.getMessage());
        }
    }
}
