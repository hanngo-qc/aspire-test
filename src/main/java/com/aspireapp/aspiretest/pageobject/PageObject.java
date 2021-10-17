package com.aspireapp.aspiretest.pageobject;

import com.aspireapp.aspiretest.helper.ElementHelper;
import com.aspireapp.aspiretest.helper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject {
    protected WebDriver driver;
    protected WaitHelper waitHelper;
    protected ElementHelper elementHelper;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        elementHelper = new ElementHelper(driver);
        PageFactory.initElements(this.driver, this);
    }
}
