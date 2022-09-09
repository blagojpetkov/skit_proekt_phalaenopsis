package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class IndexPage extends AbstractPage{

    private WebElement orchid_main;
    private WebElement osvoj_sertifikat;
    private WebElement profile;
    private WebElement login;
    private WebElement logout;
    private WebElement register;


    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public static IndexPage to(WebDriver driver){
        get(driver, "/index");
        return PageFactory.initElements(driver, IndexPage.class);
    }

    public static CertificationPage toCertificationPage(WebDriver driver, IndexPage indexPage){
        indexPage.osvoj_sertifikat.click();
        return PageFactory.initElements(driver, CertificationPage.class);
    }

    public static ProfilePage toProfilePage(WebDriver driver, IndexPage indexPage){
        indexPage.profile.click();
        return PageFactory.initElements(driver, ProfilePage.class);
    }

    public static RegisterPage toRegisterPage(WebDriver driver, IndexPage indexPage){
        indexPage.register.click();
        return PageFactory.initElements(driver, RegisterPage.class);
    }


    public void checkIfLoggedIn(){
        Assert.assertTrue(logout.isDisplayed());
    }

    public void checkIfLoggedOut(){
        Assert.assertTrue(login.isDisplayed());
    }

    public void logout(){
        logout.click();
    }

    public void checkIfRightPage(){
        Assert.assertTrue(orchid_main.isDisplayed());
    }
}
