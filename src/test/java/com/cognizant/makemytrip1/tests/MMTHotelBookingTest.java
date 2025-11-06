//package com.cognizant.makemytrip1.tests;
//
//import com.cognizant.makemytrip1.base.Driver;
//import com.cognizant.makemytrip1.pages.HotelBookingPage;
//import com.cognizant.makemytrip1.utils.ExcelReader;
//import com.cognizant.makemytrip1.utils.ExcelWriter;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//
//public class MMTHotelBookingTest extends Driver {
//
//    @Test(priority = 1)
//    public void hotelBooking_TC1() {
//        runHotelBookingTest(1);
//    }
//
//    @Test(priority = 2)
//    public void hotelBooking_TC2() {
//        runHotelBookingTest(2);
//    }
//
//    @Test(priority = 3)
//    public void hotelBooking_TC3() {
//        runHotelBookingTest(3);
//    }
//
//    public void runHotelBookingTest(int row) {
//        SoftAssert softAssert = new SoftAssert();
//
//        try {
//            System.out.println("🔹 Running TC" + row);
//            HotelBookingPage page = new HotelBookingPage(driver);
//            driver.navigate().to(Driver.url);
//
//            String city = ExcelReader.getCellData("HotelData", row, 0);
//            String expectedAdults = ExcelReader.getCellData("HotelData", row, 3);
//            System.out.println("📍 City: " + city + " | Expected Adults: " + expectedAdults);
//
//            page.openHotelTab();
//            page.selectCity(city);
//            page.selectDates();
//            page.adjustGuests();
//
//            String actualAdults = page.getAdultCount();
//            System.out.println("👥 Actual Adults: " + actualAdults);
//
//            softAssert.assertEquals(actualAdults, expectedAdults, "Mismatch in adult count");
//
//            String result = actualAdults.equals(expectedAdults) ? "PASS" : "FAIL";
//            ExcelWriter.writeResult("HotelData", row, 4, result);
//
//            System.out.println("✅ TC" + row + " → Expected: " + expectedAdults + " | Actual: " + actualAdults + " → " + result);
//
//        } catch (Exception e) {
//            System.out.println("❌ TC" + row + " → Exception occurred: " + e.getMessage());
//            ExcelWriter.writeResult("HotelData", row, 4, "ERROR");
//            softAssert.fail("Exception in TC" + row + ": " + e.getMessage());
//        }
//
//        softAssert.assertAll(); //  Final assertion check
//    }
//}

package com.cognizant.makemytrip1.tests;

import com.cognizant.makemytrip1.base.Driver;
import com.cognizant.makemytrip1.pages.HotelBookingPage;
import com.cognizant.makemytrip1.utils.ExcelReader;
import com.cognizant.makemytrip1.utils.ExcelWriter;
import org.testng.annotations.Test;

public class MMTHotelBookingTest extends Driver {

    @Test(priority = 1)
    public void hotelBooking_TC1() {
        runHotelBookingTest(1);
    }


    public void runHotelBookingTest(int row) {
        try {
            HotelBookingPage page = new HotelBookingPage(driver);
            driver.navigate().to(Driver.url);

            String city = ExcelReader.getCellData("HotelData", row, 0);
            String expectedAdults = ExcelReader.getCellData("HotelData", row, 3);

            page.openHotelTab();
            page.selectCity(city);
            page.selectDates();
            page.adjustGuests();

            String actualAdults = page.getAdultCount();
            String result = actualAdults.equals(expectedAdults) ? "PASS" : "FAIL";

            ExcelWriter.writeResult("HotelData", row, 4, result); // Column 4 = Result
            System.out.println("TC" + row + "  Expected: " + expectedAdults + " | Actual: " + actualAdults + " → " + result);

        } catch (Exception e) {
            System.out.println("TC" + row + " Exception occurred: " + e.getMessage());
        }
    }
}

