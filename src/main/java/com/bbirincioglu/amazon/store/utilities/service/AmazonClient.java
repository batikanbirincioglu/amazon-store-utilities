package com.bbirincioglu.amazon.store.utilities.service;

import com.bbirincioglu.amazon.store.utilities.ApplicationProperties;
import com.bbirincioglu.amazon.store.utilities.utils.SeleniumUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class AmazonClient implements SeleniumUtils {
    @Autowired
    private ApplicationProperties applicationProperties;

    @PostConstruct
    public void init() {
        System.setProperty(applicationProperties.getChromeDriverConfig().getPathKey(), applicationProperties.getChromeDriverConfig().getPathValue());
    }

    public String getAmazonSpecificInfo(String asinNumber, double amazonPrice, int count, int monthlySoldCount, boolean octoberDecember) {
        log.info("[getAmazonSpecificInfo()] started with asinNumber : {} amazonPrice : {} count : {} monthlySoldCount : {} octoberDecember : {}", asinNumber, amazonPrice, count, monthlySoldCount, octoberDecember);
        ChromeDriver driver = new ChromeDriver();
        System.out.println("data dir --> " + driver.getCapabilities().getCapability("userDataDir"));


        openPage(driver, "https://sellercentral.amazon.com/revcalpublic?ref=RC1&lang=en-US");
        clickElement(driver, "//kat-button[@label='Continue as guest']");
        clickElement(driver, "//kat-button[@label='Search Amazon catalog']");
        waitForIt(1000);

        clickElement(driver, "//kat-input[@label='Search for product on Amazon']");
        enterText(driver, "//kat-input[@label='Search for product on Amazon']", asinNumber);
        clickElement(driver, "//kat-button[@label='Search']");
        waitUntilElementExist(driver, "//div[@class='revenue-section']//kat-input");
        waitForIt(1000);

        clickElement(driver, "//div[@class='revenue-section']//kat-input");
        clearElement(driver, "//div[@class='revenue-section']//kat-input");
        enterText(driver, "//div[@class='revenue-section']//kat-input", String.valueOf(amazonPrice));

        if (octoberDecember) {
            clickElement(driver, "//kat-button[@label='October-December']");
            waitForIt(1000);
        }

        clickElement(findParent(findParent(findElement(driver, "//kat-label[@text='Average inventory units stored']"))), ".//kat-input");
        clearElement(findParent(findParent(findElement(driver, "//kat-label[@text='Average inventory units stored']"))), ".//kat-input");
        enterText(findParent(findParent(findElement(driver, "//kat-label[@text='Average inventory units stored']"))), ".//kat-input", String.valueOf(count));
        waitForIt(1000);

        clickElement(findParent(findElement(driver, "//kat-label[@text='Estimated monthly units sold']")), ".//kat-input");
        clearElement(findParent(findElement(driver, "//kat-label[@text='Estimated monthly units sold']")), ".//kat-input");
        enterText(findParent(findElement(driver, "//kat-label[@text='Estimated monthly units sold']")), ".//kat-input", String.valueOf(monthlySoldCount));

        String amazonFee = getAttribute(findElement(findParent(findElement(findElement(driver, "//kat-box[@id='ProgramCard']"), ".//span[contains(string(), 'Amazon fees')]")), ".//kat-label"), "text");
        System.out.println("amazonFee --> " + amazonFee);

        String fba = getAttribute(findElement(findParent(findElement(findElement(driver, "//kat-box[@id='ProgramCard']"), ".//span[contains(string(), 'Fulfillment cost')]")), ".//kat-label"), "text");
        System.out.println("fba --> " + fba);

        waitForIt(7000L);
        String pageSource = driver.getPageSource();
        driver.close();
        driver.quit();
        return pageSource;
    }
}
