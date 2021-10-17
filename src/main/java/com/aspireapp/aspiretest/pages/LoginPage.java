package com.aspireapp.aspiretest.pages;

import com.aspireapp.aspiretest.pageobject.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    @FindBy(css = "input[data-cy='register-person-username']")
    private WebElement inputPhoneEmail;

    @FindBy(css = "div[class*='q-checkbox__b']")
    private WebElement rememberCheckbox;

    @FindBy(css = "a[class*='login-step-start__register-link']")
    private WebElement registerBtn;

    @FindBy(css = "button[type='submit']")
    private WebElement nextBtn;

    @FindBy(css = "div[class*='aspire-label__text']")
    private WebElement errorLabel;

    @FindBy(css = "div[class*='aspire-modal-card']")
    private WebElement popUpChooseCountryCode;

    @FindBy(css = "button[type='button']")
    private WebElement nextBtnInPopUp;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void waitUtilPopupDisappear() {
        waitHelper.waitUntilElementDisappear(By.cssSelector("div[class*='aspire-modal-card']"));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void selectRegisterBtn() {
        elementHelper.click(registerBtn);
    }

    public void loginAccountByEmail(String account) {
        elementHelper.inputText(inputPhoneEmail, account);
        elementHelper.click(rememberCheckbox);
        elementHelper.click(nextBtn);
    }

    public void loginAccountByPhone(String account) {
        loginAccountByEmail(account);
        elementHelper.waitAndCheckElementDisplayed(popUpChooseCountryCode);
        elementHelper.click(nextBtnInPopUp);
    }

    public void loginAccountByPhoneWithCountryCode(String account) {
        elementHelper.inputText(inputPhoneEmail, "+65" + account);
        elementHelper.click(rememberCheckbox);
        elementHelper.click(nextBtn);
    }

    public void loginAccountWithoutRememberAcc(String phone) {
        elementHelper.inputText(inputPhoneEmail, phone);
        elementHelper.click(nextBtn);
    }

    public String getErrorLabel() {
        return elementHelper.getText(errorLabel);
    }
}
