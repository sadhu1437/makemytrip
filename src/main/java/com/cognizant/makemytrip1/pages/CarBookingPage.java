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
        // Use a longer wait for Jenkins, as things can be slower
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
        js = (JavascriptExecutor) driver;
    }

    // --- Locators (No Changes) ---
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

    @FindBy(xpath = "//label[@for='pickupTime']")
    public WebElement pickupTime;

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

    // --- Actions (With Fixes) ---
    
    public void openCabTab() {
        wait.until(ExpectedConditions.elementToBeClickable(cabTab)).click();
    }

    public void selectFromCity(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(fromCity)).click();
        wait.until(ExpectedConditions.visibilityOf(fromInput)).sendKeys(city);
        
        // --- FIX ---
        // Replaced Thread.sleep with an explicit wait for the suggestion to appear
        wait.until(ExpectedConditions.visibilityOf(fromSuggestion));
        wait.until(ExpectedConditions.elementToBeClickable(fromSuggestion)).click();
    }

    public void selectToCity(String city) {
        // --- FIX ---
        // Removed the complex try-catch. With proper waits, it's not needed.
        
        // Wait for the "To" input field to be ready
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='To']")));
        wait.until(ExpectedConditions.visibilityOf(toInput)).sendKeys(city);
        
        // Wait for the suggestion to appear after typing
        wait.until(ExpectedConditions.visibilityOf(toSuggestion));
        wait.until(ExpectedConditions.elementToBeClickable(toSuggestion)).click();
    }

    public void selectDepartureDate(String date) {
        String dateXpath = "//div[contains(@aria-label, '" + date + "')]";
        wait.until(ExpectedConditions.elementToBeClickable(departureDate)).click();

        WebElement dateElement;
        try {
            dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXpath)));
        } catch (Exception e) {
            System.out.println("Could not find date element with XPath: " + dateXpath);
            throw e;
        }
        js.executeScript("arguments[0].click()", dateElement);
    }

    public void selectPickupTime(String hour, String minute) {
        wait.until(ExpectedConditions.elementToBeClickable(pickupTime)).click();
        
        String formattedHour = String.format("%02d", Integer.parseInt(hour));
        
        String hourXpath = "//span[normalize-space()='" + formattedHour + " Hr']";
        String minuteXpath = "//li//span[contains(@class, 'minSlotItemChild') and contains(text(), '" + minute + "')]";
        
        WebElement hourSelect;
        try {
            // Wait for the hour to be present before finding it
            hourSelect = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(hourXpath)));
        } catch (NoSuchElementException e) {
            System.out.println("Could not find hour element with XPath: " + hourXpath);
            throw e;
        }
        
        js.executeScript("arguments[0].scrollIntoView(true);", hourSelect);
        js.executeScript("arguments[0].click()", hourSelect);
        
        WebElement minuteSelect;
        try {
            // Wait for the minute to be present before finding it
            minuteSelect = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(minuteXpath)));
        } catch (NoSuchElementException e) {
            System.out.println("Could not find minute element with XPath: " + minuteXpath);
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

        // --- FIX ---
        // Changed to a JS click. This was the exact spot that caused
        // ElementClickInterceptedException in your Jenkins logs before.
        wait.until(ExpectedConditions.elementToBeClickable(applyTime));
        js.executeScript("arguments[0].click();", applyTime);
    }

    public void searchAndFilterSUV() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(suvFilter)).click();
    }

    public String getLowestFareCar(String from, String to) {
        List<Integer> prices = new ArrayList<>();
        
        // Add a wait to make sure the car list has loaded
        wait.until(ExpectedConditions.visibilityOfAllElements(carPrices));
        
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
