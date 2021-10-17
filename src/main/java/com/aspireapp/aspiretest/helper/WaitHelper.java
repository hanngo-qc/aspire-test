package com.aspireapp.aspiretest.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUtilVisibilityLocatedByCssSelector(String cssSelector){
       new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }
    public void waitUtilTextToBeByCssSelector(String cssSelector, String text){
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.textToBe(By.cssSelector(cssSelector), text));
    }

    //This method is used to wait an element until it is displayed
    public WebElement waitUntilElementDisplayed(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
    }
    //This method is used to wait an element until it is clickable
    public WebElement waitUntilElementCanBeClicked(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(180))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void waitUntilElementDisappear(By by) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(180))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(StaleElementReferenceException.class);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
