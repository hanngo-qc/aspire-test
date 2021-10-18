package com.aspireapp.aspiretest;

import com.aspireapp.aspiretest.common.TestConstants;
import com.aspireapp.aspiretest.pages.LoginPage;
import com.aspireapp.aspiretest.pages.OtpPage;
import com.aspireapp.aspiretest.pages.RegisterPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class RegistrationTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(RegistrationTest.class);

    private HashMap testDataSuccess = new HashMap();
    private HashMap testDataFailed = new HashMap();
    private HashMap testDataRequiredField = new HashMap();
    private String validFullName = "";
    private String validPreferredName = "";
    private String validEmail = "";
    private String validPhoneNumber = "";
    private String invalidPhoneNumber = "";
    private String requiredEmail = "";
    private String requiredPhoneNumber = "";

    @BeforeTest(groups = {"Major", "Medium", "Minor"})
    public void setUpTestData() {
        testDataSuccess = (HashMap) data.get("testDataSuccess");
        testDataFailed = (HashMap) data.get("testDataFailed");
        testDataRequiredField = (HashMap) data.get("testDataRequiredField");

        validFullName = testDataSuccess.get("fullName").toString();
        validPreferredName = testDataSuccess.get("preferredName").toString();
        validEmail = testDataSuccess.get("email").toString();
        validPhoneNumber = testDataSuccess.get("phoneNumber").toString();
        invalidPhoneNumber = testDataFailed.get("phoneNumber").toString();
        requiredEmail = testDataRequiredField.get("email").toString();
        requiredPhoneNumber = testDataRequiredField.get("phoneNumber").toString();
    }

    @Test(groups = {"Major"})
    public void registerNewAccount() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.selectRegisterBtn();

        logger.info("Accessed to Register Page");
        Assert.assertEquals(registerPage.getPageTitle(), TestConstants.REGISTER_PAGE_TITLE);

        registerPage.registerNewAccount(validFullName, validPreferredName);
        logger.info("Registered successfully");
        otpPage.waitUtilPhoneOptHeaderDisplayed();
    }

    @Test(groups = {"Medium"})
    public void registerExistedAccount() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.selectRegisterBtn();
        logger.info("Accessed to Register Page");
        Assert.assertEquals(registerPage.getPageTitle(), TestConstants.REGISTER_PAGE_TITLE);
        registerPage.registerExistedAccount(validFullName, validPreferredName, validEmail, validPhoneNumber);
        registerPage.waitConfirmPopupDisplayed();
        Assert.assertEquals(registerPage.getNotificationContent(), TestConstants.EXISTED_ACCOUNT_NOTIFICATION);
        registerPage.clickLoginBtn();
        logger.info("Registered successfully");
        Assert.assertEquals(otpPage.getPageTitle(), TestConstants.OPT_PAGE_TITLE);
    }

    @Test(groups = "Minor")
    public void registerAccountWithInvalidValues() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.selectRegisterBtn();
        logger.info("Accessed to Register Page");
        Assert.assertEquals(registerPage.getPageTitle(), TestConstants.REGISTER_PAGE_TITLE);
        registerPage.registerExistedAccount(validFullName, validPreferredName, validEmail, invalidPhoneNumber);
        Assert.assertEquals(registerPage.getInvalidEmailMessage(), TestConstants.EXISTED_EMAIL_MESSAGE);
        Assert.assertEquals(registerPage.getInvalidPhoneMessage(), TestConstants.INCORRECT_PHONE_FORMAT_MESSAGE);
    }

    @Test(groups = "Minor")
    public void validateInvalidEmailAndPhoneFields() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.selectRegisterBtn();
        logger.info("Accessed to Register Page");
        Assert.assertEquals(registerPage.getPageTitle(), TestConstants.REGISTER_PAGE_TITLE);
        registerPage.inputEmail(requiredEmail);
        registerPage.inputPhoneNumber(requiredPhoneNumber);
        registerPage.clickFullName();
        Assert.assertEquals(registerPage.getInvalidEmailMessage(), TestConstants.INVALID_EMAIL_FORMAT_MESSAGE);
        Assert.assertEquals(registerPage.getInvalidPhoneMessage(), TestConstants.INVALID_DIGIT_PHONE_NUMBER_MESSAGE);
    }
}