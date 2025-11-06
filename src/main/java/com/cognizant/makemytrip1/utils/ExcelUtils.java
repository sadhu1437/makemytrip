package com.cognizant.makemytrip1.utils;

import java.io.File;
import java.util.Arrays;

public class ExcelUtils {

    public static String getLatestExcelFile(String folderPath) {
        File folder = new File(folderPath);

        // Filter only files that match the naming pattern
        File[] files = folder.listFiles((dir, name) ->
            name.startsWith("TestData_") && name.endsWith(".xlsx")
        );

        if (files == null || files.length == 0) {
            System.out.println("❌ No matching Excel files found in: " + folderPath);
            return null;
        }

        // Sort files by last modified time (descending)
        Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));

        return files[0].getAbsolutePath(); // Return the most recent file
    }
}
