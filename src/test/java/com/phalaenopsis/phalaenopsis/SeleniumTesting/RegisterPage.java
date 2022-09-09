package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.h2.mvstore.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends AbstractPage{

    private WebElement error;
    private WebElement username;
    private WebElement name_user;
    private WebElement surname_user;
    private WebElement password;
    private WebElement repeatPassword;
    private WebElement submit_register;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage register(String username1, String name_user1, String surname_user1, String password1, String repeatPassword1){
        username.sendKeys(username1);
        name_user.sendKeys(name_user1);
        surname_user.sendKeys(surname_user1);
        password.sendKeys(password1);
        repeatPassword.sendKeys(repeatPassword1);
        submit_register.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
}
