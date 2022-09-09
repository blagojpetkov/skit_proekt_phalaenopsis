package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StemPage extends AbstractPage{
    private WebElement stem;
    public StemPage(WebDriver driver) {
        super(driver);
    }

    public void CheckIfRightPage(){
        Assert.assertTrue(stem.isDisplayed());
    }
}
