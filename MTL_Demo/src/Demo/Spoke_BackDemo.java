package Demo;

import org.testng.annotations.Test;

import MTL_Archive.Repository_Archive;

import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Spoke_BackDemo {
	
	AndroidDriver driver;
	
// MTL app launching method
	@BeforeTest
	 public void setup_SpokeBack() throws MalformedURLException
	 {
		
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 }
	
// Transition from spoke screen to homescreen	
	@Test
	public void test_SpokeBack() throws TimeoutException,InterruptedException
{
		MobileElement hamburger_button =(MobileElement)(driver.findElement(By.id(Repository_Archive.HamburgerMenu_id)));
		hamburger_button.click();
		
		while(driver.findElements(By.xpath(Repository_Archive.CategoryRibbon_xpath)).size()==0)
		{
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0,scrollStart,0,scrollEnd,2000);
		}
		
		if(driver.findElements(By.xpath(Repository_Archive.CategoryRibbon_xpath)).size()>0)
		{
			driver.findElement(By.xpath(Repository_Archive.CategoryRibbon_xpath)).click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		}   
// wait for 9 second		
		  try{Thread.sleep(9000);
		  }
		  catch(Exception e){} 
		
System.out.println("Press android back key");
		((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.BACK);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement weather_icon=driver.findElement(By.xpath(Repository_Archive.weatherIcon_xpath));
		System.out.println("Weather icon displayed :" +weather_icon.isDisplayed());
System.out.println("Weather existence under homepage is verified");
	
}

// Method to close the launched MTL app from foreground	
	@AfterTest
	    public void close() {	
System.out.println("App moved from foreground to foreground");
		driver.closeApp();
		
    }
}
