package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends AbstractPage{

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void checkSearchResult(int topicsCount){
        int numOfTopics = driver.findElements(By.className("topic")).size();
        Assert.assertEquals(topicsCount, numOfTopics);
    }
}
