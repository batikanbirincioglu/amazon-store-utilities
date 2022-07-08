package com.bbirincioglu.amazon.store.utilities.service;

import com.bbirincioglu.amazon.store.utilities.ApplicationProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonClient {
    @Autowired
    private ApplicationProperties applicationProperties;

    public String getAmazonSpecificInfo(String asinNumber) {
        System.setProperty(applicationProperties.getChromeDriverConfig().getPathKey(), applicationProperties.getChromeDriverConfig().getPathValue());
        System.out.println(applicationProperties.getChromeDriverConfig().getPathKey());

        String url = "https://sellercentral.amazon.com/revcalpublic?ref=RC1&lang=en-US";
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        driver.findElements(By.xpath("//kat-button[@label='Continue as guest']")).get(0).click();
        driver.findElements(By.xpath("//kat-button[@label='Search Amazon catalog']")).get(0).click();
        sleep(3000);
        WebElement asinInput = driver.findElements(By.xpath("//kat-input[@label='Search for product on Amazon']")).get(0);
        asinInput.click();
        asinInput.sendKeys(asinNumber);
        driver.findElements(By.xpath("//kat-button[@label='Search']")).get(0).click();
        sleep(3000);
        String pageSource = driver.getPageSource();
        driver.close();
        return pageSource;
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {

        }
    }
}
