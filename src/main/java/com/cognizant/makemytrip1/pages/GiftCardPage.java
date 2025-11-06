package com.cognizant.makemytrip1.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class GiftCardPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    // Locators
    @FindBy(xpath = "//li[@data-cy='menu_More']")
    public WebElement moreButton;

    @FindBy(xpath = "//a[@data-cy='submenu_Giftcards']")
    public WebElement giftcardsLink;

    @FindBy(xpath = "(//h3[contains(text(),'Gift Card')])[1]/ancestor::div[contains(@class,'card__data')]/img")
    public WebElement giftCardImage;

    @FindBy(name = "senderName")
    public WebElement senderName;

    @FindBy(name = "senderMobileNo")
    public WebElement senderMobile;

    @FindBy(name = "senderEmailId")
    public WebElement senderEmail;

    @FindBy(xpath = "//button[@data-cy='BookingDetails_440']")
    public WebElement buyButton;

    @FindBy(xpath = "//p[text()='Please enter a valid Email id.']")
    public WebElement errorMessage;

    //Actions
    public void openGiftCardMenu() throws InterruptedException {
        js.executeScript("window.scrollBy(0,500)", "");
        wait.until(ExpectedConditions.visibilityOf(moreButton));
        js.executeScript("arguments[0].scrollIntoView(true);", moreButton);
        Thread.sleep(1000);
        moreButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(giftcardsLink)).click();
    }

    public void selectGiftCardImage() {
        wait.until(ExpectedConditions.elementToBeClickable(giftCardImage)).click();
    }

    public void fillSenderDetails(String name, String mobile, String email) {
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("senderEmailId")));
        js.executeScript("arguments[0].scrollIntoView(true);", emailField);

        senderName.sendKeys(name);
        senderMobile.sendKeys(mobile);
        senderEmail.sendKeys(email);
    }

    public void submitGiftCard() {
        wait.until(ExpectedConditions.elementToBeClickable(buyButton)).click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
