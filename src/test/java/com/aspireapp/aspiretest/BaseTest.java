package com.aspireapp.aspiretest;

import com.aspireapp.aspiretest.common.Constants;
import com.aspireapp.aspiretest.utils.ConvertUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

public class BaseTest {
    private static final Logger logger = Logger.getLogger(BaseTest.class);
    protected static Map<Object, Object> data;
    protected WebDriver driver;

    @BeforeSuite(groups = {"Major", "Medium", "Minor"})
    public void setupTestData() {
        logger.info("Initializing Staging env");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver94.0");
        driver = new ChromeDriver();
        data = ConvertUtil.convertJsonFileToMap(Constants.STAGE_TEST_DATA_NAME);
    }

    @BeforeMethod(groups = {"Major", "Medium", "Minor"})
    public void accessPage() {

        driver.get("https://feature-qa-automation.customer-frontend.staging.aspireapp.com/sg/login");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Fail to wait for page load.");
        }
    }

    @AfterMethod(groups = {"Major", "Medium", "Minor"})
    public void afterFinishTest() {
        driver.quit();
    }
}
