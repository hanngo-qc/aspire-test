package com.aspireapp.aspiretest.pages;

import com.aspireapp.aspiretest.pageobject.PageObject;
import com.aspireapp.aspiretest.utils.DataGenerationUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class RegisterPage extends PageObject {
    private static final Logger logger = Logger.getLogger(RegisterPage.class);

    @FindBy(css = "input[data-cy='register-person-name']")
    private WebElement fullName;

    @FindBy(css = "input[data-cy='register-person-preferred-name']")
    private WebElement preferredName;

    @FindBy(css = "input[data-cy='register-person-email']")
    private WebElement email;

    @FindBy(css = "input[data-cy='register-person-phone']")
    private WebElement phoneNumber;

    @FindBy(css = "div[data-cy='register-person-email']>div>div[class*='text-negative']")
    private WebElement errorEmailMessage;

    @FindBy(css = "div[data-cy='register-person-phone']>div>div[class*='text-negative']")
    private WebElement errorPhoneNumberMessage;

    @FindBy(css = "div[data-cy='register-person-heard-about']")
    private WebElement registerPersonHeard;

    @FindBy(css = "div[role='listbox']")
    private WebElement listBoxRegisterPerson;

    @FindBy(css = "div[data-cy='register-person-privacy']")
    private WebElement agreeCheckBox;

    @FindBy(css = "button[class*='aspire-button--cta']")
    private WebElement continueBtn;

    @FindBy(css = "div[class='q-card']")
    private WebElement confirmPopUp;

    @FindBy(css = "div[class*='text-subtitle1']")
    private WebElement notificationContent;

    @FindBy(css = "a[class*='aspire-button--cta']")
    private WebElement loginBtn;

    @FindBy(css = "img[class='q-mt-md q-mr-md cursor-pointer']")
    private WebElement closePopupBtn;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void registerUnrequiredFields(String validFullName, String validPreferredName) {
        elementHelper.inputText(fullName, validFullName);
        elementHelper.inputText(preferredName, validPreferredName);
        elementHelper.click(registerPersonHeard);
        elementHelper.waitAndCheckElementDisplayed(listBoxRegisterPerson);
        listBoxRegisterPerson.findElements(By.cssSelector("div[role='option']")).get(2).click();
        elementHelper.click(agreeCheckBox);
    }

    public void registerRequiredFields(String validEmail, String validPhoneNumber) {
        elementHelper.inputText(email, validEmail);
        elementHelper.inputText(phoneNumber, validPhoneNumber);
    }

    public void clickContinueBtn() {
        elementHelper.click(continueBtn);
    }

    public void registerExistedAccount(String validFullName, String validPreferredName, String validEmail, String validPhoneNumber) {
        registerUnrequiredFields(validFullName, validPreferredName);
        registerRequiredFields(validEmail, validPhoneNumber);
        clickContinueBtn();
    }

    public void registerNewAccount(String validFullName, String validPreferredName) {
        registerUnrequiredFields(validFullName, validPreferredName);
        String validPhoneNumber = DataGenerationUtil.generatePhoneNumber(6);
        String validEmail = DataGenerationUtil.generateGmail(4);
        registerRequiredFields(validEmail, validPhoneNumber);
        clickContinueBtn();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            notificationContent = driver.findElement(By.cssSelector("div[class*='text-subtitle1']"));
            while (notificationContent != null) {
                logger.info("Existed Email/Phone number. Generate new Email/Phone number to Register Account");
                elementHelper.click(closePopupBtn);
                clearEmail();
                clearPhoneNumber();
                validPhoneNumber = DataGenerationUtil.generatePhoneNumber(6);
                validEmail = DataGenerationUtil.generateGmail(4);
                registerRequiredFields(validEmail, validPhoneNumber);
                clickContinueBtn();
                try {
                    notificationContent = driver.findElement(By.cssSelector("div[class*='text-subtitle1']"));
                } catch (NoSuchElementException exception) {
                    break;
                }
            }
        } catch (NoSuchElementException exception) {
            logger.info("Register Account successfully");
        }
    }

    public void waitConfirmPopupDisplayed() {
        elementHelper.waitAndCheckElementDisplayed(confirmPopUp);
    }

    public String getNotificationContent() {
        return elementHelper.getText(notificationContent);
    }

    public String getInvalidEmailMessage() {
        return elementHelper.getText(errorEmailMessage);
    }

    public String getInvalidPhoneMessage() {
        return elementHelper.getText(errorPhoneNumberMessage);
    }

    public void inputEmail(String validEmail) {
        elementHelper.inputText(email, validEmail);
    }

    public void clearEmail() {
        elementHelper.clearText(email);
    }

    public void inputPhoneNumber(String validPhoneNumber) {
        elementHelper.inputText(phoneNumber, validPhoneNumber);
    }

    public void clearPhoneNumber() {
        elementHelper.clearText(phoneNumber);
    }

    public void clickLoginBtn() {
        elementHelper.click(loginBtn);
    }
}
