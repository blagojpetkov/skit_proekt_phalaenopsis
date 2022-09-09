package com.phalaenopsis.phalaenopsis.SeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CertificationPage extends AbstractPage{

    private WebElement question1;
    private WebElement question2;
    private WebElement question3;
    private WebElement question4;
    private WebElement question5;
    private WebElement question6;

    private WebElement submit;
    public CertificationPage(WebDriver driver) {
        super(driver);
    }

    public void CheckIfRightPage(){
        Assert.assertTrue(question1.isDisplayed());
    }

    public void answerQuestions(int answer1, int answer2, int answer3,
                                int answer4, int answer5, int answer6) throws InterruptedException {
        //2,1,2,1,1,2 are all correct answers
        Select question1Select = new Select(question1);
        question1Select.deselectAll();

        Select question2Select = new Select(question2);
        question2Select.deselectAll();

        Select question3Select = new Select(question3);
        question3Select.deselectAll();

        Select question4Select = new Select(question4);
        question4Select.deselectAll();

        Select question5Select = new Select(question5);
        question5Select.deselectAll();

        Select question6Select = new Select(question6);
        question6Select.deselectAll();

        question1Select.selectByIndex(answer1);
        Thread.sleep(500);
        question2Select.selectByIndex(answer2);
        Thread.sleep(500);
        question3Select.selectByIndex(answer3);
        Thread.sleep(500);
        question4Select.selectByIndex(answer4);
        Thread.sleep(500);
        question5Select.selectByIndex(answer5);
        Thread.sleep(500);
        question6Select.selectByIndex(answer6);
        Thread.sleep(1000);
        submit.click();
    }

}
