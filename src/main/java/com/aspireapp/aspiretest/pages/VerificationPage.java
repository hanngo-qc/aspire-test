package com.aspireapp.aspiretest.pages;

import com.aspireapp.aspiretest.pageobject.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class VerificationPage extends PageObject {
    public static final Logger logger = Logger.getLogger(VerificationPage.class);

    public VerificationPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
