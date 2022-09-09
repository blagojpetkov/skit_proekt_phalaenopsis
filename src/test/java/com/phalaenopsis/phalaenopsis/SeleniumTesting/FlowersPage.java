package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlowersPage extends AbstractPage{
    private WebElement flowers;
    public FlowersPage(WebDriver driver) {
        super(driver);
    }

    public void CheckIfRightPage(){
        Assert.assertTrue(flowers.isDisplayed());
    }
}
