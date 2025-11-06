package com.cognizant.makemytrip1.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {

    public static String getCellData(String sheetName, int rowNum, int colNum) {
        try {
            FileInputStream fis = new FileInputStream("data/TestData1.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            workbook.close();
            return cell.toString();
        } catch (Exception e) {
            System.out.println("Excel read error: " + e.getMessage());
            return "";
        }
    }
}
