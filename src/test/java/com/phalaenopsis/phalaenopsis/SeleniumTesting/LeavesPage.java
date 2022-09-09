package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeavesPage extends AbstractPage{
    private WebElement leaves;
    public LeavesPage(WebDriver driver) {
        super(driver);
    }
    public void CheckIfRightPage(){
        Assert.assertTrue(leaves.isDisplayed());
    }
}
