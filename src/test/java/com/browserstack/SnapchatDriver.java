package com.browserstack;

import com.ctc.wstx.evt.WEntityDeclaration;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class SnapchatDriver extends IOSDriver {

  public SnapchatDriver(HttpCommandExecutor executor, Capabilities capabilities) {
    super(executor, capabilities);
  }

  public SnapchatDriver(URL remoteAddress, Capabilities capabilities) {
    super(remoteAddress, capabilities);
  }

  public SnapchatDriver(URL remoteAddress, HttpClient.Factory httpClientFactory, Capabilities capabilities) {
    super(remoteAddress, httpClientFactory, capabilities);
  }

  public SnapchatDriver(AppiumDriverLocalService service, Capabilities capabilities) {
    super(service, capabilities);
  }

  public SnapchatDriver(AppiumDriverLocalService service, HttpClient.Factory httpClientFactory, Capabilities capabilities) {
    super(service, httpClientFactory, capabilities);
  }

  public SnapchatDriver(AppiumServiceBuilder builder, Capabilities capabilities) {
    super(builder, capabilities);
  }

  public SnapchatDriver(AppiumServiceBuilder builder, HttpClient.Factory httpClientFactory, Capabilities capabilities) {
    super(builder, httpClientFactory, capabilities);
  }

  public SnapchatDriver(HttpClient.Factory httpClientFactory, Capabilities capabilities) {
    super(httpClientFactory, capabilities);
  }

  public SnapchatDriver(ClientConfig clientConfig, Capabilities capabilities) {
    super(clientConfig, capabilities);
  }

  public SnapchatDriver(AppiumClientConfig appiumClientConfig, Capabilities capabilities) {
    super(appiumClientConfig, capabilities);
  }

  public SnapchatDriver(URL remoteSessionAddress) {
    super(remoteSessionAddress);
  }

  public SnapchatDriver(Capabilities capabilities) {
    super(capabilities);
  }

  //METHODS

  //clicks an element based on its accessibility id as soon as possible. Time to timeout in case it doesn't show up
  public void clickIfExistsByAccessibilityId(String accessibilityId, int waitTimeInSeconds) {
    try {
      WebElement element = (WebElement) new WebDriverWait(this, Duration.ofSeconds(waitTimeInSeconds)).until(
              ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(accessibilityId)));
      element.click();
      Thread.sleep((int)Math.random() * 1200);
    } catch (NoSuchElementException | TimeoutException e) {
      System.out.println("Element not found: " + accessibilityId);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void enterKeysByAccessibilityId(String keys, String accessibilityId, int waitTimeInSeconds) {
    try {
      WebElement element = (WebElement) new WebDriverWait(this, Duration.ofSeconds(waitTimeInSeconds)).until(
              ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(accessibilityId)));
      for (String bite : new StringSplitter().splitStringRandomly(keys)) {
        element.sendKeys(bite);
        Thread.sleep((int)Math.random() * 100);
      }
      Thread.sleep((int)Math.random() * 1200);
    } catch (NoSuchElementException | TimeoutException e) {
      System.out.println("Element not found: " + accessibilityId);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  //Logs the driver in the first time to the first account
  public void firstLogin(String user, String pass) throws InterruptedException {

    //Find the login button upon installation and click it
    this.clickIfExistsByAccessibilityId("LOG IN", 30);
    System.out.println("First login button clicked");

    //Find the username field and enter the username
    this.enterKeysByAccessibilityId(user, "username", 30);
    System.out.println("username entered");

    //Find the show/hide password button and click it
    this.clickIfExistsByAccessibilityId("show_hide_password", 30);
    System.out.println("password show button pressed");

    //Find the password field and enter the password
    this.enterKeysByAccessibilityId(pass, "password_textField", 30);
    System.out.println("password entered");

    //Find the login button and click it
    this.clickIfExistsByAccessibilityId("login_btn_login", 30);
    System.out.println("log in button pressed");

    //Find the don't allow button for the notifications popup and click it if it exists
    try {
      WebElement dontAllowNotifications = (WebElement) new WebDriverWait(this, Duration.ofSeconds(5)).until(
              ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//XCUIElementTypeOther[@name=\"Horizontal scroll bar, 1 page\"])[2]")));
      dontAllowNotifications.click();
    } catch (NoSuchElementException | TimeoutException e) {
      System.out.println("Element not found: Don't Allow button on notifications popup");
    }

    //Find the allow button for the camera popup and click it if it exists
    this.clickIfExistsByAccessibilityId("Allow", 5);

    //Find the don't allow button for the photo library popup and click it if it exists
    this.clickIfExistsByAccessibilityId("Don’t Allow", 5);

    //Waiting so that the following findElement doesn't accidentally find the element from the previous statement
    Thread.sleep(500);

    //Find the don't allow button for the microphone access popup and click it if it exists
    this.clickIfExistsByAccessibilityId("Don’t Allow", 5);

    //Waiting so that the following findElement doesn't accidentally find the element from the previous statement
    Thread.sleep(500);

    //Find the don't allow button for the contacts access popup and click it if it exists
    this.clickIfExistsByAccessibilityId("Don't Allow", 5);

    //Wait 1 second and then click the middle of the screen to get rid of the 'save password' popup
    Thread.sleep(1000);
    new Actions(this).moveByOffset(20, 20).click();

    //Find the IGNORE button for the mic permission popup and click it if it exists
    this.clickIfExistsByAccessibilityId("alert_view_action_button_IGNORE", 5);

    //End of the method
    Thread.sleep(5000);
  }
}
