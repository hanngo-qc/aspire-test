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

    private static final String NOTIFICATION_MESSAGE_CSS_SELECTOR = "div[class*='text-subtitle1']";
    private static final String OPTION_REGISTER_PERSON_CSS_SELECTOR = "div[role='option']";

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

    @FindBy(css = "input[role='combobox']")
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
        selectOptionRegisterPerson();
        elementHelper.click(agreeCheckBox);
    }

    public void selectOptionRegisterPerson(){
        elementHelper.click(registerPersonHeard);
        elementHelper.waitAndCheckElementDisplayed(listBoxRegisterPerson);
        listBoxRegisterPerson.findElements(By.cssSelector(OPTION_REGISTER_PERSON_CSS_SELECTOR)).get(0).click();
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
            notificationContent = driver.findElement(By.cssSelector(NOTIFICATION_MESSAGE_CSS_SELECTOR));
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
                    notificationContent = driver.findElement(By.cssSelector(NOTIFICATION_MESSAGE_CSS_SELECTOR));
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

    public void clickFullName(){
        elementHelper.click(fullName);
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
        elementHelper.click(email);
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
