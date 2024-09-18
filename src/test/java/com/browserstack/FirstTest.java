package com.browserstack;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class FirstTest extends AppiumTest {

  @Test
  public void test() throws Exception {
    //Click the login button when the app is first loaded up
//    WebElement logInButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("LOG IN")));
//    logInButton.click();

    String secretUser = "";
    String secretPass = "";

    driver.firstLogin(secretUser, secretPass);

    Thread.sleep(5000);

//    Assert.assertEquals(textOutput.getText(),"hello@browserstack.com");
  }
}
