package com.cognizant.makemytrip1.tests;

import com.cognizant.makemytrip1.base.Driver;
import com.cognizant.makemytrip1.pages.CarBookingPage;
import com.cognizant.makemytrip1.utils.ExcelReader;
import com.cognizant.makemytrip1.utils.ExcelWriter;
import org.testng.annotations.Test;

public class MMTCarBookingTest extends Driver {

    CarBookingPage cp;

    @Test(priority = 1)
    public void cabSearch_TC1() throws InterruptedException {
        runCabSearchTest(1);
    }

    @Test(priority = 2)
    public void cabSearch_TC2() throws InterruptedException {
        runCabSearchTest(2);
    }

    @Test(priority = 3)
    public void cabSearch_TC3() throws InterruptedException {
        runCabSearchTest(3);
    }

//    @Test(priority = 4)
//    public void cabSearch_TC4() throws InterruptedException {
//        runCabSearchTest(4);
//    }
//
//    @Test(priority = 5)
//    public void cabSearch_TC5() throws InterruptedException {
//        runCabSearchTest(5);
//    }
    
//    @Test(priority = 6)
//    public void cabSearch_TC6() throws InterruptedException {
//        runCabSearchTest(6);
//    }
//
//    @Test(priority = 7)
//    public void cabSearch_TC7() throws InterruptedException {
//        runCabSearchTest(7);
//    }
//
//    @Test(priority = 8)
//    public void cabSearch_TC8() throws InterruptedException {
//        runCabSearchTest(8);
//    }
//
//    @Test(priority = 9)
//    public void cabSearch_TC9() throws InterruptedException {
//        runCabSearchTest(9);
//    }
//
//    @Test(priority = 10)
//    public void cabSearch_TC10() throws InterruptedException {
//        runCabSearchTest(10);
//    }
    	

    public void runCabSearchTest(int row) throws InterruptedException {
        cp = new CarBookingPage(driver);
        driver.navigate().to(Driver.url);

        String from = ExcelReader.getCellData("CarData", row, 0);
        String to = ExcelReader.getCellData("CarData", row, 1);
        String date = ExcelReader.getCellData("CarData", row, 2);
        String hour = ExcelReader.getCellData("CarData", row, 3);
        String minute = ExcelReader.getCellData("CarData", row, 4);
        String meridian = ExcelReader.getCellData("CarData", row, 5);

        cp.openCabTab();
        cp.selectFromCity(from);
        cp.selectToCity(to);
        cp.selectDepartureDate(date);
        cp.selectPickupTime(hour, minute);
        cp.searchAndFilterSUV();

        String lowestFare = cp.getLowestFareCar(from ,to);
        ExcelWriter.writeResult("CarData", row, 6, lowestFare);
        System.out.println("Lowest fare for row " + row + ": " + lowestFare);
    }
}
