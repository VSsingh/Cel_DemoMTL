package Demo;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Search1 {
	
AppiumDriver driver;

//Capabilities of apk going to automate
@BeforeTest
public void Setup() throws MalformedURLException
{
	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setCapability("No-reset", true);
	cap.setCapability("full-reset", false);
/*	cap.setCapability("unicodeKeyboard", true);
	cap.setCapability("resetKeyboard", true);  */
	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508fam-6b3f9b22");
	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
	cap.setCapability("version", "6.0.1");
	cap.setCapability("appPackage", "jp.co.necp.mytimeline");
	cap.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
	driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
}

@Test(priority=0)
public void search() throws TimeoutException,InterruptedException
{
System.out.println("Clicking on Search magnifying glass");
      String ribbonName = "Intel";
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      WebElement searchIcon =driver.findElement(By.id("jp.co.necp.mytimeline:id/header_search_button"));
      searchIcon.click();
      WebDriverWait waitx = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
		waitx.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/search_input")));
      WebElement searchField =driver.findElement(By.id("jp.co.necp.mytimeline:id/search_input"));

      searchField.sendKeys(ribbonName);
      driver.hideKeyboard();
      WebElement search =driver.findElement(By.xpath("//android.widget.ImageButton[contains(@resource-id,'jp.co.necp.mytimeline:id/search_button')]"));
      search.click();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      WebElement registerribbon = driver.findElement(By.name("キーワードをマイリボンに登録"));
      registerribbon.click();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      WebElement foundkeyword =driver.findElement(By.name("Intel"));
      String ribbonValue =foundkeyword.getText();
      AssertJUnit.assertEquals("Intel1", ribbonValue);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
}

/* @AfterTest
public void closeddriver() throws InterruptedException
{
	 System.out.println("Hello");
	//driver.quit();
	
}
*/
}
