package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CertificatePage extends AbstractPage {

    private WebElement download_certificate;

    public CertificatePage(WebDriver driver) {
        super(driver);
    }

    public void CheckIfRightPage(){
        Assert.assertTrue(download_certificate.isDisplayed());
    }

    public void downloadCertificate(){
        download_certificate.click();
    }

}
