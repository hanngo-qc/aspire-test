package com.aspireapp.aspiretest;

import com.aspireapp.aspiretest.common.TestConstants;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aspireapp.aspiretest.pages.LoginPage;
import com.aspireapp.aspiretest.pages.OtpPage;

import java.util.HashMap;

public class LoginTest extends BaseTest {
    public static final Logger logger = Logger.getLogger(LoginTest.class);

    private HashMap testDataSuccess = new HashMap();
    private HashMap testDataFailed = new HashMap();
    private String validEmail = "";
    private String invalidEmail = "";
    private String validPhoneNumber = "";
    private String invalidPhoneNumber = "";

    @BeforeTest(groups = {"Major","Medium", "Minor"})
    public void setUpTestData() {
        testDataSuccess = (HashMap) data.get("testDataSuccess");
        testDataFailed = (HashMap) data.get("testDataFailed");
        validEmail = testDataSuccess.get("email").toString();
        invalidEmail = testDataFailed.get("email").toString();
        validPhoneNumber = testDataSuccess.get("phoneNumber").toString();
        invalidPhoneNumber = testDataFailed.get("phoneNumber").toString();
    }

    @Test(groups = {"Major"})
    public void loginAccountByEmail() {
        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccountByEmail(validEmail);

        logger.info("Access to OPT page");
        Assert.assertEquals(otpPage.getPageTitle(), TestConstants.OPT_PAGE_TITLE);
    }

    @Test(groups = {"Major"})
    public void loginAccountByPhoneNumber() {
        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccountByPhone(validPhoneNumber);

        logger.info("Access to OPT page");
        Assert.assertEquals(otpPage.getPageTitle(), TestConstants.OPT_PAGE_TITLE);
    }

    @Test(groups = {"Medium"})
    public void loginAccountByPhoneNumberWithCountryCode() {
        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccountByPhoneWithCountryCode(validPhoneNumber);

        logger.info("Access to OPT page");
        Assert.assertEquals(otpPage.getPageTitle(), TestConstants.OPT_PAGE_TITLE);
    }

    @Test(groups = {"Medium"})
    public void loginAccountFailedEmail() {
        LoginPage loginPage = new LoginPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccountByEmail(invalidEmail);
        Assert.assertEquals(loginPage.getErrorLabel(), TestConstants.INVALID_EMAIL_MESSAGE);
    }

    @Test(groups = "Medium")
    private void loginAccountFailedPhoneNumber(){
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccountByPhone(invalidPhoneNumber);
        loginPage.waitUtilPopupDisappear();
        Assert.assertEquals(loginPage.getErrorLabel(), TestConstants.INVALID_PHONE_NUMBER_MESSAGE);
    }

    @Test(groups = {"Minor"})
    public void loginAccountWithoutRememberAcc() {
        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccountWithoutRememberAcc(validPhoneNumber);

        logger.info("Access to OPT page");
        Assert.assertEquals(otpPage.getPageTitle(), TestConstants.OPT_PAGE_TITLE);
    }
}
