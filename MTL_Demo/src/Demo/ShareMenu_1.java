package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class ShareMenu_1 {
	
AndroidDriver driver;
	
	
	//Capabilities of apk going to automate
	@BeforeTest
	 public void setup_RibbRegist() throws MalformedURLException	
	 {
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("no-reset", "true");
		cap.setCapability("full-reset", "false");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508baw-LenovoTAB2A8");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
		cap.setCapability("version", "5.0.2");
		
		cap.setCapability("appPackage", "jp.co.necp.mytimeline");
		cap.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	//	 driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	 }
@Test
public void shareWindow()
{
	WebElement BigTile =driver.findElement(By.id("jp.co.necp.mytimeline:id/big_tile"));
//	WebElement text = BigTile.findElement(By.id("jp.co.necp.mytimeline:id/article_title"));
	  TouchAction Action = new TouchAction(driver); // Long press method
	  Action.longPress(driver.findElement(By.id("jp.co.necp.mytimeline:id/big_tile"))).perform();
	  WebDriverWait wait=new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/pocket_btn_image")));
	  String result = driver.findElement(By.id("jp.co.necp.mytimeline:id/pocket_btn_label")).getText();

}

}
