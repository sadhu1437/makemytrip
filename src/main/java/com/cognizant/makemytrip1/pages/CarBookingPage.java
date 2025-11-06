//package com.cognizant.makemytrip1.pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.ArrayList;
//
//public class CarBookingPage {
//    WebDriver driver;
//    WebDriverWait wait;
//    JavascriptExecutor js;
//
//    public CarBookingPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        js = (JavascriptExecutor) driver;
//    }
//
//    // Locators
//    @FindBy(css = "a[href*='/cabs/']")
//    public WebElement cabTab;
//
//    @FindBy(xpath = "//*[@id='fromCity']")
//    public WebElement fromCity;
//
//    @FindBy(xpath = "//input[@placeholder='From']")
//    public WebElement fromInput;
//
//    @FindBy(xpath = "//ul[@role = 'listbox']/li[1]")
//    public WebElement fromSuggestion;
//
//    @FindBy(xpath = "//input[@placeholder='To']")
//    public WebElement toInput;
//
//    @FindBy(xpath = "//ul[@role = 'listbox']/li[1]")
//    public WebElement toSuggestion;
//
//    @FindBy(xpath = "//label[@for='departure']")
//    public WebElement departureDate;
//
//    @FindBy(xpath = "//div[@aria-label='Wed Nov 12 2025']")
//    public WebElement actualDate;
//
//    @FindBy(xpath = "//label[@for='pickupTime']")
//    public WebElement pickupTime;
//
//    @FindBy(xpath = "//span[text()='07  Hr']")
//    public WebElement hourSelect;
//
//    @FindBy(xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div[2]/div[1]/div[5]/div[1]/div[2]/ul[2]/li[7]")
//    public WebElement minuteSelect;
//
//    @FindBy(xpath = "//li[@data-cy='MeridianSlots_82'][1]")
//    public WebElement meridianSelect;
//
//    @FindBy(xpath = "//span[text()='APPLY']")
//    public WebElement applyTime;
//
//    @FindBy(xpath = "//a[text()='Search']")
//    public WebElement searchButton;
//
//    @FindBy(xpath = "//span[text()='SUV']")
//    public WebElement suvFilter;
//
//    @FindBy(css = "span.cabDetailsCard_title__ptEiS")
//    public List<WebElement> carNames;
//
//    @FindBy(className = "cabDetailsCard_price__SHN6W")
//    public List<WebElement> carPrices;
//
//    // Actions
//    public void openCabTab() {
//        wait.until(ExpectedConditions.elementToBeClickable(cabTab)).click();
//    }
//
//    public void selectFromCity(String city) throws InterruptedException {
//        wait.until(ExpectedConditions.elementToBeClickable(fromCity)).click();
//        wait.until(ExpectedConditions.visibilityOf(fromInput)).sendKeys(city);
//        Thread.sleep(5000);
//        wait.until(ExpectedConditions.elementToBeClickable(fromSuggestion)).click();
//    }
//
//    public void selectToCity(String city) throws InterruptedException {
//        wait.until(ExpectedConditions.visibilityOf(toInput)).sendKeys(city);
//        Thread.sleep(5000);
//        wait.until(ExpectedConditions.elementToBeClickable(toSuggestion)).click();
//    }
//
//    public void selectDepartureDate() {
//        wait.until(ExpectedConditions.elementToBeClickable(departureDate)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(actualDate));
//        js.executeScript("arguments[0].click()", actualDate);
//    }
//
//    public void selectPickupTime() {
//        wait.until(ExpectedConditions.elementToBeClickable(pickupTime)).click();
//        js.executeScript("arguments[0].scrollIntoView(true);", hourSelect);
//        js.executeScript("arguments[0].click()", hourSelect);
//        js.executeScript("arguments[0].scrollIntoView(true);", minuteSelect);
//        js.executeScript("arguments[0].click()", minuteSelect);
//        wait.until(ExpectedConditions.elementToBeClickable(meridianSelect)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(applyTime)).click();
//    }
//
//    public void searchAndFilterSUV() {
//        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(suvFilter)).click();
//    }
//
//    public String getLowestFareCar() {
//        List<Integer> prices = new ArrayList<>();
//        for (WebElement ele : carPrices) {
//            String p = ele.getText().replace("₹", "").replace(",", "").trim();
//            prices.add(Integer.parseInt(p));
//        }
//
//        int min = prices.get(0);
//        int lowPriceIndex = 0;
//        for (int i = 1; i < prices.size(); i++) {
//            if (prices.get(i) < min) {
//                min = prices.get(i);
//                lowPriceIndex = i;
//            }
//        }
//
//        return "Lowest Cab fare from to  is: " + carNames.get(lowPriceIndex).getText() + " ₹" + min;
//    }
//
//}

package com.cognizant.makemytrip1.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class CarBookingPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public CarBookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    // Locators
    @FindBy(css = "a[href*='/cabs/']")
    public WebElement cabTab;

    @FindBy(xpath = "//*[@id='fromCity']")
    public WebElement fromCity;

    @FindBy(xpath = "//input[@placeholder='From']")
    public WebElement fromInput;

    @FindBy(xpath = "//ul[@role = 'listbox']/li[1]")
    public WebElement fromSuggestion;

    @FindBy(xpath = "//input[@placeholder='To']")
    public WebElement toInput;

    @FindBy(xpath = "//ul[@role = 'listbox']/li[1]")
    public WebElement toSuggestion;

    @FindBy(xpath = "//label[@for='departure']")
    public WebElement departureDate;

//    @FindBy(xpath = "//div[@aria-label='Wed Nov 12 2025']")
//    public WebElement actualDate;

    @FindBy(xpath = "//label[@for='pickupTime']")
    public WebElement pickupTime;

//    @FindBy(xpath = "//span[text()='07  Hr']")
//    public WebElement hourSelect;

//    @FindBy(xpath = "//li//span[contains(@class, 'minSlotItemChild') and contains(text(), '30')]")
//    public WebElement minuteSelect;

    @FindBy(xpath = "//li[@data-cy='MeridianSlots_82'][1]")
    public WebElement meridianSelect;

    @FindBy(xpath = "//li[@data-cy='scrolled-currency-dropdown']")
    public List<WebElement> currencyOverlay;

    @FindBy(xpath = "//span[text()='APPLY']")
    public WebElement applyTime;

    @FindBy(xpath = "//a[text()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//span[text()='SUV']")
    public WebElement suvFilter;

    @FindBy(css = "span.cabDetailsCard_title__ptEiS")
    public List<WebElement> carNames;

    @FindBy(className = "cabDetailsCard_price__SHN6W")
    public List<WebElement> carPrices;

    // Actions
    public void openCabTab() {
        wait.until(ExpectedConditions.elementToBeClickable(cabTab)).click();
    }

    public void selectFromCity(String city) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(fromCity)).click();
        wait.until(ExpectedConditions.visibilityOf(fromInput)).sendKeys(city);
        Thread.sleep(5000); // mandatory wait for suggestions to load
        wait.until(ExpectedConditions.elementToBeClickable(fromSuggestion)).click();
    }

    public void selectToCity(String city) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='To']")));
            wait.until(ExpectedConditions.visibilityOf(toInput)).sendKeys(city);
            Thread.sleep(3000); // mandatory wait for suggestions to load
            wait.until(ExpectedConditions.elementToBeClickable(toSuggestion)).click();
        } catch (TimeoutException e) {
            System.out.println("Retrying To city input after refresh...");
            driver.navigate().refresh();
            openCabTab();
            selectFromCity(city);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='To']")));
            wait.until(ExpectedConditions.visibilityOf(toInput)).sendKeys(city);
            Thread.sleep(3000); // Retry wait
            wait.until(ExpectedConditions.elementToBeClickable(toSuggestion)).click();
        }
    }

    public void selectDepartureDate(String date) {
    	
    	String dateXpath = "//div[contains(@aria-label, '" + date + "')]";
    	wait.until(ExpectedConditions.elementToBeClickable(departureDate)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(actualDate));
//        js.executeScript("arguments[0].click()", actualDate);
    	WebElement dateElement;
        try {
            dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXpath)));
        } catch (Exception e) {
            System.out.println("Could not find date element with XPath: " + dateXpath);
            throw e;
        }
        js.executeScript("arguments[0].click()", dateElement);
    }

    public void selectPickupTime(String hour,  String minute) {
        wait.until(ExpectedConditions.elementToBeClickable(pickupTime)).click();
        
        String formattedHour = String.format("%02d", Integer.parseInt(hour));
        
        String hourXpath = "//span[normalize-space()='" + formattedHour + " Hr']";
        String minuteXpath = "//li//span[contains(@class, 'minSlotItemChild') and contains(text(), '" + minute + "')]";
        
        WebElement hourSelect;
        try {
            hourSelect = driver.findElement(By.xpath(hourXpath));
        } catch (NoSuchElementException e) {
            System.out.println("Could not find hour element with XPath: " + hourXpath);
            // Optionally, close the popup or throw the exception
            // js.executeScript("arguments[0].click()", closeButton); // If you have a close button
            throw e;
        }
        
        
        
        js.executeScript("arguments[0].scrollIntoView(true);", hourSelect);
        js.executeScript("arguments[0].click()", hourSelect);
        
        WebElement minuteSelect;
        try {
            minuteSelect = driver.findElement(By.xpath(minuteXpath));
        } catch (NoSuchElementException e) {
            System.out.println("Could not find minute element with XPath: " + minuteXpath);
            // Optionally, close the popup or throw the exception
            // js.executeScript("arguments[0].click()", closeButton); // If you have a close button
            throw e;
        }
     

        js.executeScript("arguments[0].scrollIntoView(true);", minuteSelect);
        js.executeScript("arguments[0].click()", minuteSelect);

        if (!currencyOverlay.isEmpty()) {
            try {
                wait.until(ExpectedConditions.invisibilityOfAllElements(currencyOverlay));
            } catch (TimeoutException e) {
                System.out.println("Currency overlay did not disappear. Proceeding anyway.");
            }
        }

        try {
            new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class)
                .until(ExpectedConditions.elementToBeClickable(meridianSelect)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click()", meridianSelect);
        }

        wait.until(ExpectedConditions.elementToBeClickable(applyTime)).click();
    }

    public void searchAndFilterSUV() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(suvFilter)).click();
    }

    public String getLowestFareCar(String from, String to) {
        List<Integer> prices = new ArrayList<>();
        for (WebElement ele : carPrices) {
            String p = ele.getText().replace("₹", "").replace(",", "").trim();
            prices.add(Integer.parseInt(p));
        }

        int min = prices.get(0);
        int lowPriceIndex = 0;
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) < min) {
                min = prices.get(i);
                lowPriceIndex = i;
            }
        }
        
        return "Lowest Cab fare from " + from + " to " + to + " is: " + carNames.get(lowPriceIndex).getText() + " ₹" + min;
    }
}

