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

    // Utility: Safe click with fallback
    public void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    // Actions
    public void openCabTab() {
        safeClick(cabTab);
    }

    public void selectFromCity(String city) throws InterruptedException {
        safeClick(fromCity);
        wait.until(ExpectedConditions.visibilityOf(fromInput)).sendKeys(city);
        Thread.sleep(5000); // wait for suggestions
        safeClick(fromSuggestion);
    }

    public void selectToCity(String city) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='To']")));
            wait.until(ExpectedConditions.visibilityOf(toInput)).sendKeys(city);
            Thread.sleep(3000);
            safeClick(toSuggestion);
        } catch (TimeoutException e) {
            System.out.println("Retrying To city input after refresh...");
            driver.navigate().refresh();
            openCabTab();
            selectFromCity(city);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='To']")));
            wait.until(ExpectedConditions.visibilityOf(toInput)).sendKeys(city);
            Thread.sleep(3000);
            safeClick(toSuggestion);
        }
    }

    public void selectDepartureDate(String date) {
        String dateXpath = "//div[contains(@aria-label, '" + date + "')]";
        safeClick(departureDate);
        try {
            WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXpath)));
            js.executeScript("arguments[0].click();", dateElement);
        } catch (Exception e) {
            System.out.println("Could not find date element: " + dateXpath);
            throw e;
        }
    }

    public void selectPickupTime(String hour, String minute) {
        safeClick(pickupTime);

        String formattedHour = String.format("%02d", Integer.parseInt(hour));
        String hourXpath = "//span[normalize-space()='" + formattedHour + " Hr']";
        String minuteXpath = "//li//span[contains(@class, 'minSlotItemChild') and contains(text(), '" + minute + "')]";

        try {
            WebElement hourSelect = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(hourXpath)));
            safeClick(hourSelect);

            WebElement minuteSelect = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(minuteXpath)));
            safeClick(minuteSelect);

            if (!currencyOverlay.isEmpty()) {
                try {
                    wait.until(ExpectedConditions.invisibilityOfAllElements(currencyOverlay));
                } catch (TimeoutException e) {
                    System.out.println("Currency overlay did not disappear. Proceeding anyway.");
                }
            }

            try {
                safeClick(meridianSelect);
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", meridianSelect);
            }

            int attempts = 0;
            while (attempts < 3) {
                try {
                    safeClick(applyTime);
                    break;
                } catch (Exception e) {
                    System.out.println("Retrying APPLY click...");
                    Thread.sleep(1000);
                    attempts++;
                }
            }

        } catch (Exception e) {
            System.err.println("Error during pickup time selection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void searchAndFilterSUV() {
        safeClick(searchButton);
        safeClick(suvFilter);
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
