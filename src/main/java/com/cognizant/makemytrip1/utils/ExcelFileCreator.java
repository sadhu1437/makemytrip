package com.cognizant.makemytrip1.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileCreator {

    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();

        // Sheet 1: CarData
        Sheet carSheet = workbook.createSheet("CarData");
        String[] carHeaders = {"FromCity", "ToCity", "Date", "Hour", "Minute", "Meridian", "Result"};
        String[][] carData = {
            {"Delhi", "Manali", "12", "7", "30", "AM", ""},
            {"Mumbai", "Pune", "13", "8", "20", "AM", ""},
            {"Hyderabad", "Vizag", "12", "9", "40", "AM", ""}
//            {"Bangalore", "Mysore", "14", "9", "10", "AM", ""},
//            {"Chennai", "Coorg", "15", "4", "40", "AM", ""},
            
//            {"Kolkata", "Digha", "12", "7", "30", "AM", ""},
//            {"Ahmedabad", "Surat", "12", "7", "30", "AM", ""},
//            {"Jaipur", "Udaipur", "12", "7", "30", "AM", ""},
//            {"Lucknow", "Varanasi", "12", "7", "30", "AM", ""},
//            {"Bhopal", "Indore", "12", "7", "30", "AM", ""},
            
        };
        createSheetWithData(carSheet, carHeaders, carData);

        // Sheet 2: GiftCardData
        Sheet giftSheet = workbook.createSheet("GiftCardData");
        String[] giftHeaders = {"SenderName", "Mobile", "Email", "ExpectedError", "Result"};
        String[][] giftData = {
            {"Sandeep", "7382231051", "Sandeep#gmail.com", "Please enter a valid Email id.", ""},
            {"Ravi", "9876543210", "ravi@", "Please enter a valid Email id.", ""},
            {"Priya", "9123456789", "priya.gmail.com", "Please enter a valid Email id.", ""}
        };
        createSheetWithData(giftSheet, giftHeaders, giftData);

        // Sheet 3: HotelData
        Sheet hotelSheet = workbook.createSheet("HotelData");
        String[] hotelHeaders = {"City", "CheckInDate", "CheckOutDate", "ExpectedAdults", "Result"};
        String[][] hotelData = {
            {"Manali", "12", "13", "3", ""}
        };
        createSheetWithData(hotelSheet, hotelHeaders, hotelData);

        //Ensure /data folder exists
        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        //Save the file
        try (FileOutputStream fos = new FileOutputStream("data/TestData1.xlsx")) {
            workbook.write(fos);
            workbook.close();
            System.out.println("Excel file 'TestData.xlsx' created with CarData, GiftCardData, and HotelData sheets.");
        } catch (IOException e) {
            System.out.println("Error writing Excel file: " + e.getMessage());
        }
    }

    private static void createSheetWithData(Sheet sheet, String[] headers, String[][] data) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < data[i].length; j++) {
                row.createCell(j).setCellValue(data[i][j]);
            }
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
