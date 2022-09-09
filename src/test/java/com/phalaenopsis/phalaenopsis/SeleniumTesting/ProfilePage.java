package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends AbstractPage {
    private WebElement submit;
    private WebElement FirstName;
    private WebElement surname;
    private WebElement password;
    private WebElement repeatPassword;

    public void CheckIfRightPage(){
        Assert.assertTrue(submit.isDisplayed());
    }


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage updateProfile(String name, String surname, String password, String repeatPassword) throws InterruptedException {
        Thread.sleep(1000);
        this.FirstName.clear();
        Thread.sleep(500);
        this.FirstName.sendKeys(name);
        Thread.sleep(500);
        this.surname.clear();
        this.surname.sendKeys(surname);
        Thread.sleep(500);

        this.password.sendKeys(password);
        Thread.sleep(500);
        this.repeatPassword.sendKeys(repeatPassword);
        Thread.sleep(1000);
        submit.click();

        return PageFactory.initElements(driver, ProfilePage.class);
    }
}
