package com.aspireapp.aspiretest.pages;

import com.aspireapp.aspiretest.common.TestConstants;
import com.aspireapp.aspiretest.pageobject.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class OtpPage extends PageObject {
    public static final Logger logger = Logger.getLogger(OtpPage.class);

    private static final String HEADER_TITLE_CSS_SELECTOR = "div[class*='text-h4']";

    @FindBy(css = "div[class*='digit-input__box']>div[class*='digit-input__input']")
    private List<WebElement> otpInputElements;

    @FindBy(css = "input[data-cy='digit-input']")
    private WebElement otpInput;

    @FindBy(css = "button[data-cy='verify-otp-request-new']")
    private WebElement requestNewBtn;

    @FindBy(css = "")
    private WebElement confirmPopup;

    public OtpPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitUtilEmailOptHeaderDisplayed() {
        waitHelper.waitUtilTextToBeByCssSelector(HEADER_TITLE_CSS_SELECTOR, TestConstants.EMAIL_OPT_PAGE_HEADER);
    }

    public void waitUtilPhoneOptHeaderDisplayed() {
        waitHelper.waitUtilTextToBeByCssSelector(HEADER_TITLE_CSS_SELECTOR, TestConstants.PHONE_OPT_PAGE_HEADER);
    }

    public void sendRequestNew() {
        elementHelper.click(requestNewBtn);
        elementHelper.waitAndCheckElementDisplayed(confirmPopup);
    }

}
