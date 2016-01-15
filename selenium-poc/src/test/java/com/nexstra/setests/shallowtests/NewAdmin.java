package com.nexstra.setests.shallowtests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.nexstra.setests.profile.TestingProfile;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NewAdmin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private TestingProfile profile ;

  @Before
  public void setUp() throws Exception {

	  profile =  TestingProfile.load();
	  System.out.println("Profile = > "+profile);
    driver = profile.getDriver();
    baseUrl = profile.getBaseUrl();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSmoke2() throws Exception {
    driver.get(baseUrl + "/admin/");
    driver.findElement(By.cssSelector("input.gwt-TextBox")).clear();
    driver.findElement(By.cssSelector("input.gwt-TextBox")).sendKeys(profile.getAdminUser());
    driver.findElement(By.cssSelector("input.gwt-PasswordTextBox")).clear();
    driver.findElement(By.cssSelector("input.gwt-PasswordTextBox")).sendKeys(profile.getAdminPassword());
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    try {
      assertTrue(driver.findElement(By.cssSelector("td > table > tbody > tr > td > div.gwt-Label")).isDisplayed());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
