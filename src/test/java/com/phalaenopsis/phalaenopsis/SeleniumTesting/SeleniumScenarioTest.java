package com.phalaenopsis.phalaenopsis.SeleniumTesting;


import org.h2.index.Index;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    private ChromeDriver driver;


    @BeforeEach
    private void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        this.driver = new ChromeDriver(options);
    }

    @AfterEach
    private void destroy(){
        if(this.driver!=null){
            this.driver.close();
        }
    }

    @Test
    public void registerUser() throws InterruptedException {
        IndexPage indexPage = IndexPage.to(driver);
        indexPage.checkIfRightPage();
        RegisterPage registerPage = IndexPage.toRegisterPage(driver, indexPage);
        Thread.sleep(500);
        registerPage.register("user0",
                "Nikola",
                "Nikolovski",
                "password",
                "password");

        Assert.assertEquals("http://localhost:9999/register?error=true", driver.getCurrentUrl());


        registerPage.register("username",
                "Nikola",
                "Nikolovski",
                "password",
                "Password");

        Assert.assertEquals("http://localhost:9999/register?error=true", driver.getCurrentUrl());

        LoginPage loginPage = registerPage.register("username",
                "Nikola",
                "Nikolovski",
                "password",
                "password");

        Assert.assertEquals("http://localhost:9999/register", driver.getCurrentUrl());
        Thread.sleep(200);
        indexPage = LoginPage.doLogin(driver, loginPage, "username", "password");
        Thread.sleep(200);
        indexPage.checkIfRightPage();
        indexPage.checkIfLoggedIn();
        Thread.sleep(1000);



    }

    @Test
    public void testDownloadCertificate() throws Exception{
        Thread.sleep(500);
        IndexPage indexPage = IndexPage.to(driver);
        indexPage.checkIfRightPage();
        Thread.sleep(500);
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        Thread.sleep(500);
        indexPage = LoginPage.doLogin(this.driver, loginPage, "user0", "pass0");
        indexPage.checkIfRightPage();
        Thread.sleep(500);
        CertificationPage certificationPage = IndexPage.toCertificationPage(driver, indexPage);
        certificationPage.CheckIfRightPage();
        //2,1,2,1,1,2 are all correct answers

        Thread.sleep(500);
        //invalid answers
        certificationPage.answerQuestions(3,2,3,2,3,1);
        Thread.sleep(1000);
        Assert.assertEquals("http://localhost:9999/certification?error=true", driver.getCurrentUrl());

        Thread.sleep(1000);


        certificationPage.answerQuestions(1,2,0,2,2,1);
        Thread.sleep(1000);
        Assert.assertEquals("http://localhost:9999/certification?error=true", driver.getCurrentUrl());

        Thread.sleep(1000);


        certificationPage.answerQuestions(2,1,2,1,1,2);
        Thread.sleep(1000);
        Assert.assertEquals("http://localhost:9999/certification", driver.getCurrentUrl());
        //certification is passed
        Thread.sleep(500);

        driver.findElement(By.id("link")).click();
        Thread.sleep(500);
        CertificatePage certificatePage = PageFactory.initElements(driver, CertificatePage.class);

        certificatePage.CheckIfRightPage();
        Thread.sleep(1000);
        certificatePage.downloadCertificate();

        Thread.sleep(1000);

    }


    @Test
    public void testUpdateUserInfo() throws InterruptedException {
        Thread.sleep(500);
        IndexPage indexPage = IndexPage.to(driver);
        indexPage.checkIfRightPage();
        Thread.sleep(500);
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        Thread.sleep(500);
        indexPage = LoginPage.doLogin(this.driver, loginPage, "user0", "pass0");
        indexPage.checkIfRightPage();
        Thread.sleep(500);
        ProfilePage profilePage = IndexPage.toProfilePage(driver, indexPage);
        profilePage.CheckIfRightPage();
        Thread.sleep(1000);
        profilePage = profilePage.updateProfile("NewName", "NewSurname", "password", "password1");
        Assert.assertEquals("http://localhost:9999/profile?error=true", driver.getCurrentUrl());
        Thread.sleep(1000);
        profilePage.updateProfile("NewName", "NewSurname", "password", "password");
        Assert.assertEquals("http://localhost:9999/profile?success=true", driver.getCurrentUrl());
        Thread.sleep(1000);
        indexPage = profilePage.getIndexPage();
        Thread.sleep(500);
        indexPage.checkIfLoggedIn();
        indexPage.logout();
        indexPage.checkIfLoggedOut();


        Thread.sleep(1000);
    }

    @Test
    public void testUpdateUserInfo_checkIfUpdatedUserInfo() throws InterruptedException {
        Thread.sleep(500);
        IndexPage indexPage = IndexPage.to(driver);
        indexPage.checkIfRightPage();
        Thread.sleep(500);
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        Thread.sleep(500);
        indexPage = LoginPage.doLogin(this.driver, loginPage, "user0", "password");
        indexPage.checkIfLoggedIn();
        Thread.sleep(500);
        indexPage.logout();
        indexPage.checkIfLoggedOut();
        Thread.sleep(2000);
    }

    @Test
    public void testCheckViews() throws InterruptedException {
        Thread.sleep(500);
        IndexPage indexPage = IndexPage.to(driver);
        RootsPage rootsPage = indexPage.getRootsPage();
        Thread.sleep(500);

        driver.findElement(By.id("white")).click();
        Thread.sleep(500);
        rootsPage.getRootsPage();
        Thread.sleep(500);

        driver.findElement(By.id("green")).click();
        Thread.sleep(500);
        rootsPage.getRootsPage();
        Thread.sleep(500);

        driver.findElement(By.id("yellow")).click();
        Thread.sleep(500);
        rootsPage.getRootsPage();
        Thread.sleep(500);

        driver.findElement(By.id("black")).click();
        Thread.sleep(500);
        rootsPage.getRootsPage();
        Thread.sleep(500);

        driver.findElement(By.id("brown")).click();
        Thread.sleep(500);
        rootsPage.getRootsPage();
        Thread.sleep(500);

        driver.findElement(By.id("air")).click();
        Thread.sleep(500);
        rootsPage.getRootsPage();
        Thread.sleep(500);



        LeavesPage leavesPage = rootsPage.getLeavesPage();
        leavesPage.CheckIfRightPage();
        Thread.sleep(500);

        StemPage stemPage = leavesPage.getStemPage();
        stemPage.CheckIfRightPage();
        Thread.sleep(500);

        FlowersPage flowersPage = stemPage.getFlowersPage();
        flowersPage.CheckIfRightPage();
        Thread.sleep(2000);
    }

    @Test
    public void testSearch() throws InterruptedException {
        IndexPage indexPage = IndexPage.to(driver);
        Thread.sleep(300);

        SearchPage searchPage = indexPage.search("корен");
        Thread.sleep(1000);
        searchPage.checkSearchResult(6);
        Thread.sleep(500);

        searchPage = searchPage.search("лист");
        Thread.sleep(1000);
        searchPage.checkSearchResult(1);
        Thread.sleep(500);

        searchPage = searchPage.search("стебло");
        Thread.sleep(1000);
        searchPage.checkSearchResult(1);
        Thread.sleep(500);

        searchPage = searchPage.search("цвет");
        Thread.sleep(1000);
        searchPage.checkSearchResult(1);
        Thread.sleep(500);

        searchPage = searchPage.search("а");
        Thread.sleep(1000);
        searchPage.checkSearchResult(6);
        Thread.sleep(500);

        searchPage = searchPage.search("асдф");
        Thread.sleep(1000);
        searchPage.checkSearchResult(0);
        Thread.sleep(500);

        searchPage = searchPage.search("");
        Thread.sleep(1000);
        searchPage.checkSearchResult(9);
        Thread.sleep(500);

    }

}
