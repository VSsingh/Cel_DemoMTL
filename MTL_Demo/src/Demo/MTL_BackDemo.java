package Demo;

import org.testng.annotations.Test;

import MTL_Archive.Repository_Archive;
import MTL_Methods_Archive.Methods_Archive;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class MTL_BackDemo {
	
	AndroidDriver driver;
	
// MTL app launching method
	
	@BeforeTest
	 public void setup_MtlBack() throws MalformedURLException
{	 
	 Methods_Archive.MTL_Setup();
	 
 
	 //helllo
	/*
		System.out.println("setting desired capabilities");
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("no-reset", "true");
		cap.setCapability("full-reset", "false");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508fam-6b39b22");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
		cap.setCapability("version", "6.0.1");
		cap.setCapability("appPackage", "jp.co.necp.mytimeline");
		cap.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "90");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
		*/
}
	 
// Method to identify weather icon and press android back key to move app in background		
	@Test
	public void test_weather() 
	{
		
		 System.out.println("Navigating to homescreen");		 		
	     WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 10).ignoring(TimeoutException.class,NoSuchElementException.class);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.weatherIcon_id))); 
		 WebElement weather_icon = driver.findElement(By.id(Repository_Archive.weatherIcon_id));
		 System.out.println("Weather icon displayed value : " +weather_icon.isDisplayed()); 
		 System.out.println("Weather icon under homepage is verified");
		  
// wait for 9 sec to load homescreen
			  try{Thread.sleep(9000);
			  }
			  catch(Exception e){} //every 30sec
			    ((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.BACK);
			 
		        	System.out.println("Application back key is pressed");
		
	}
		
// Method to close the launched MTL app from foreground	
	
	@AfterTest
	public void close_Home() 
	{
		System.out.println("hello");
	//	driver.closeApp();
		
    }
	
	}
