package com.cognizant.makemytrip1.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HotelBookingPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public HotelBookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    // ✅ Locators
    @FindBy(css = "a[href*='/hotels/']")
    public WebElement hotelTab;

    @FindBy(xpath = "//span[@data-cy='hotelCityLabel']")
    public WebElement cityLabel;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement hotelCityInput;

    @FindBy(xpath = "//*[@id=\"top-banner\"]/div[2]/div/div[1]/div[2]/div/div[1]/div[1]/div[1]/div/div/div/div[1]/input")
    public WebElement cityInput;

    @FindBy(xpath = "(//*[contains(text(), 'City in')])[2]")
    public WebElement suggestedCity;

    @FindBy(xpath = "//div[@class='DayPicker-Day']/span[text()='12']")
    public WebElement checkInDate;

    @FindBy(xpath = "//div[@class='DayPicker-Day']/span[text()='13']")
    public WebElement checkOutDate;

    @FindBy(xpath = "//button[@aria-label='Increase value from 2']")
    public WebElement increaseAdults;
    
//    @FindBy(xpath = "//span[@class='appendRight10']/span[@class='lineHeight36 latoBlack appendRight5 font30']")
//    public WebElement guestCountLabel;
//
//    @FindBy(xpath = "(//button[contains(@aria-label, 'Increase value from')])[2]")
//    public WebElement increaseAdults;
//
//    @FindBy(xpath = "(//button[contains(@aria-label, 'Decrease value from')])[2]")
//    public WebElement decreaseAdults;

    @FindBy(xpath = "//button[text()='APPLY']")
    public WebElement applyButton;

    @FindBy(xpath = "//label[@for='guest']/p/span/span[3]")
    public WebElement adultCount;

    // ✅ Actions
    public void openHotelTab() {
        wait.until(ExpectedConditions.elementToBeClickable(hotelTab)).click();
    }

    public void selectCity(String cityName) {
        wait.until(ExpectedConditions.elementToBeClickable(cityLabel));
        js.executeScript("arguments[0].scrollIntoView(true);", cityLabel);
        cityLabel.click();

        wait.until(ExpectedConditions.elementToBeClickable(hotelCityInput)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cityInput)).sendKeys(cityName);
        wait.until(ExpectedConditions.elementToBeClickable(suggestedCity)).click();
    }

    public void selectDates() {
        wait.until(ExpectedConditions.elementToBeClickable(checkInDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutDate)).click();
    }

    public void adjustGuests() {
        wait.until(ExpectedConditions.elementToBeClickable(increaseAdults)).click();
        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
    }

    public String getAdultCount() {
        wait.until(ExpectedConditions.visibilityOf(adultCount));
        return adultCount.getText();
    }
}
