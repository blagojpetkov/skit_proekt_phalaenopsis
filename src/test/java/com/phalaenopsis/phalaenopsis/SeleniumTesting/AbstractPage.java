package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    static void get(WebDriver driver, String relativeUrl){
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        driver.get(url);
    }


    public IndexPage getIndexPage() {
        driver.findElement(By.id("index")).click();
        return PageFactory.initElements(driver, IndexPage.class);
    }

    public RootsPage getRootsPage(){
        driver.findElement(By.id("roots")).click();
        return PageFactory.initElements(driver, RootsPage.class);
    }

    public LeavesPage getLeavesPage(){
        driver.findElement(By.id("leaves")).click();
        return PageFactory.initElements(driver, LeavesPage.class);
    }

    public StemPage getStemPage(){
        driver.findElement(By.id("stem")).click();
        return PageFactory.initElements(driver, StemPage.class);
    }

    public FlowersPage getFlowersPage(){
        driver.findElement(By.id("flowers")).click();
        return PageFactory.initElements(driver, FlowersPage.class);
    }

    public SearchPage search(String search){
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).sendKeys(search);
        driver.findElement(By.id("submit_search")).click();
        return PageFactory.initElements(driver, SearchPage.class);
    }
}
