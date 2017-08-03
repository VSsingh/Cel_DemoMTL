package Demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class MTL_BackDemo {
	
	AndroidDriver driver;
	
	
	//Capabilities of apk going to automate
	@BeforeClass
	
	 public void setup_MtlBack() throws MalformedURLException
	 
	 {
		
		
		System.out.println("Ssetting desired capibilities");
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("no-reset", "true");
		cap.setCapability("full-reset", "false");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508fam-6b3f9b22");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
		cap.setCapability("version", "6.0.1");
		
		cap.setCapability("appPackage", "jp.co.necp.mytimeline");
		cap.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	 }
	
	
	@Test
	public void test_Home() throws Exception
	{
			
		
		 System.out.println("Hello");
		WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 20).ignoring(TimeoutException.class,NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/weather_icon")));
		  System.out.println("Weather icon under homepage is verified");
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    ((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.BACK);
	  
		System.out.println("Application back key is pressed");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
		
	
/*	@AfterClass
	public void close_Home() 
	{
		System.out.println("hello");
	//	driver.closeApp();
		
    }

*/	
}
