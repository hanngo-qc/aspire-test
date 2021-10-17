package com.aspireapp.aspiretest;

import com.aspireapp.aspiretest.common.TestConstants;
import com.aspireapp.aspiretest.pages.LoginPage;
import com.aspireapp.aspiretest.pages.OtpPage;
import com.aspireapp.aspiretest.pages.VerificationPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class OtpTest extends BaseTest {
    public static final Logger logger = Logger.getLogger(OtpTest.class);

    private HashMap testDataSuccess = new HashMap();
    private HashMap testDataFailed = new HashMap();
    private String validEmail = "";
    private String validOptCode = "";
    private String invalidOptCode = "";

    @BeforeTest(groups = {"Major", "Medium", "Minor"})
    public void setUpTestData() {
        testDataSuccess = (HashMap) data.get("testDataSuccess");
        testDataFailed = (HashMap) data.get("testDataFailed");
        validEmail = testDataSuccess.get("email").toString();
        validOptCode = testDataSuccess.get("optCode").toString();
        invalidOptCode = testDataFailed.get("optCode").toString();
    }

    @Test(groups = {"Major"})
    public void verifyOPTSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);
        VerificationPage verificationPage = new VerificationPage(driver);

        loginPage.loginAccountByEmail(validEmail);
        logger.info("Login successfully");
        otpPage.waitUtilEmailOptHeaderDisplayed();
        //input OPT code
    }

    @Test(groups = {"Minor"})
    public void verifyOPTFailed() {
        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);
        VerificationPage verificationPage = new VerificationPage(driver);

        logger.info("Accessed Login Page");
        loginPage.loginAccountByEmail(validEmail);
        logger.info("Login successfully");
        Assert.assertEquals(otpPage.getPageTitle(), TestConstants.OPT_PAGE_TITLE);
        //input OPT code
        otpPage.sendRequestNew();
    }
}
