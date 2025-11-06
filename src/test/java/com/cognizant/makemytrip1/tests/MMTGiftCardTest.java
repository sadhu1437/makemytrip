//package com.cognizant.makemytrip1.tests;
//
//import com.cognizant.makemytrip1.base.Driver;
//import com.cognizant.makemytrip1.pages.GiftCardPage;
//import com.cognizant.makemytrip1.utils.ExcelReader;
//import com.cognizant.makemytrip1.utils.ExcelWriter;
//import com.cognizant.makemytrip1.utils.ScreenshotUtil;
//import org.testng.annotations.Test;
//
//public class MMTGiftCardTest extends Driver {
//
//    @Test(priority = 1)
//    public void runGiftCardTest_TC1() {
//        runGiftCardTest(1);
//    }
//
//    @Test(priority = 2)
//    public void runGiftCardTest_TC2() {
//        runGiftCardTest(2);
//    }
//
//    @Test(priority = 3)
//    public void runGiftCardTest_TC3() {
//        runGiftCardTest(3);
//    }
//
//    public void runGiftCardTest(int row) {
//        try {
//            GiftCardPage page = new GiftCardPage(driver);
//            driver.navigate().to(Driver.url);
//
//            page.openGiftCardMenu();
//            page.selectGiftCardImage();
//
//            String name = ExcelReader.getCellData("GiftCardData", row, 0);
//            String mobile = ExcelReader.getCellData("GiftCardData", row, 1);
//            String email = ExcelReader.getCellData("GiftCardData", row, 2);
//            String expectedError = ExcelReader.getCellData("GiftCardData", row, 3);
//
//            page.fillSenderDetails(name, mobile, email);
//            page.submitGiftCard();
//
//            String actualError = page.getErrorMessage();
//            ScreenshotUtil.capture(driver, "GiftCardError_TC" + row);
//
//            String result = actualError.equals(expectedError) ? "PASS" : "FAIL";
//            ExcelWriter.writeResult("GiftCardData", row, 4, result); // Column 4 = Result
//
//            System.out.println("TC" + row + " Expected: " + expectedError + " | Actual: " + actualError + " -> " + result);
//
//        } catch (Exception e) {
//            ScreenshotUtil.capture(driver, "GiftCardFlowError_TC" + row);
//            System.out.println("TC" + row + " Exception occurred: " + e.getMessage());
//        }
//    }
//}

package com.cognizant.makemytrip1.tests;

import com.cognizant.makemytrip1.base.Driver;
import com.cognizant.makemytrip1.pages.GiftCardPage;
import com.cognizant.makemytrip1.utils.ExcelReader;
import com.cognizant.makemytrip1.utils.ExcelWriter;
import com.cognizant.makemytrip1.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MMTGiftCardTest extends Driver {

    @Test(priority = 1)
    public void runGiftCardTest_TC1() {
        runGiftCardTest(1);
    }

    @Test(priority = 2)
    public void runGiftCardTest_TC2() {
        runGiftCardTest(2);
    }

    @Test(priority = 3)
    public void runGiftCardTest_TC3() {
        runGiftCardTest(3);
    }

    public void runGiftCardTest(int row) {
        try {
            GiftCardPage page = new GiftCardPage(driver);
            driver.navigate().to(Driver.url);

            page.openGiftCardMenu();
            page.selectGiftCardImage();

            String name = ExcelReader.getCellData("GiftCardData", row, 0);
            String mobile = ExcelReader.getCellData("GiftCardData", row, 1);
            String email = ExcelReader.getCellData("GiftCardData", row, 2);
            String expectedError = ExcelReader.getCellData("GiftCardData", row, 3);

            page.fillSenderDetails(name, mobile, email);
            page.submitGiftCard();

            String actualError = page.getErrorMessage();
            ScreenshotUtil.capture(driver, "GiftCardError_TC" + row);

            try {
                Assert.assertEquals(actualError, expectedError, "Mismatch in error message");
                ExcelWriter.writeResult("GiftCardData", row, 4, "PASS");
                System.out.println("TC" + row + " Expected: " + expectedError + " | Actual: " + actualError + " -> PASS");
            } catch (AssertionError ae) {
                ExcelWriter.writeResult("GiftCardData", row, 4, "FAIL");
                System.out.println("TC" + row + " Expected: " + expectedError + " | Actual: " + actualError + " -> FAIL");
                throw ae;
            }

        } catch (Exception e) {
            ScreenshotUtil.capture(driver, "GiftCardFlowError_TC" + row);
            ExcelWriter.writeResult("GiftCardData", row, 4, "ERROR");
            System.out.println("TC" + row + " Exception occurred: " + e.getMessage());
            Assert.fail("Test case TC" + row + " failed due to exception: " + e.getMessage());
        }
    }
}

