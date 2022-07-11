package com.bbirincioglu.amazon.store.utilities.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface SeleniumUtils {
    default void openPage(WebDriver driver, String url) {
        driver.get(url);
    }

    default String getAttribute(WebElement element, String name) {
        return element.getAttribute(name);
    }

    default WebElement findElement(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    default WebElement findElement(WebElement parent, String xpath) {
        return parent.findElement(By.xpath(xpath));
    }

    default WebElement findParent(WebElement element) {
        return element.findElement(By.xpath("./.."));
    }

    default void clickElement(WebDriver driver, String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    default void clickElement(WebElement parent, String xpath) {
        parent.findElement(By.xpath(xpath)).click();
    }

    default void enterText(WebDriver driver, String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    default void enterText(WebElement parent, String xpath, String text) {
        parent.findElement(By.xpath(xpath)).sendKeys(text);
    }

    default void getText(WebDriver driver, String xpath) {
        driver.findElement(By.xpath(xpath)).getAttribute("value");
    }

    default void getText(WebElement parent, String xpath) {
        parent.findElement(By.xpath(xpath)).getAttribute("value");
    }

    default void clearElement(WebDriver driver, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.sendKeys(Keys.END);
        while (!(element.getAttribute("value") == null || element.getAttribute("value").length() == 0)) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    default void clearElement(WebElement parent, String xpath) {
        WebElement element = parent.findElement(By.xpath(xpath));
        element.sendKeys(Keys.END);
        while (!(element.getAttribute("value") == null || element.getAttribute("value").length() == 0)) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    default void waitUntilElementExist(WebDriver driver, String xpath) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    default void waitUntilElementClickable(WebDriver driver, String xpath) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    default void waitForIt(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {

        }
    }
}
