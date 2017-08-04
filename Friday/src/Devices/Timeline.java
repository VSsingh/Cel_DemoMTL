package Devices;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Timeline {
AndroidDriver driver;
	
	@BeforeClass
	public void setup() throws MalformedURLException{
		
		//Created object of DesiredCapabilities class
	
		DesiredCapabilities capabilty=new DesiredCapabilities();
		
		capabilty.setCapability("no-reset", "true");
		capabilty.setCapability("full-reset", "False");
		capabilty.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilty.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508fam-6b3f9b22");
		capabilty.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
		
		capabilty.setCapability("version", "6.0.1");
		
		capabilty.setCapability("appPackage","jp.co.necp.mytimeline");
		
		capabilty.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
		
		// Initialize the driver object with the URL to Appium Server and pass capabilities
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilty);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(priority=0)
	public void launch(){
		System.out.println("Launching MTL");
		
	}
	
	
	//@Test(priority=1)
	@Test(invocationCount=10)
	public void Hamburger() throws InterruptedException
	{
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		MobileElement hamburger_button =(MobileElement)(driver.findElement(By.className("android.widget.ImageButton")));
		hamburger_button.click();
		while(driver.findElements(By.name("モータースポーツ")).size()==0)
		{
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0,scrollStart,0,scrollEnd,2000);
		}
	
		if(driver.findElements(By.name("モータースポーツ")).size()>0)
		{
			driver.findElement(By.name("モータースポーツ")).click();
		}   
		MobileElement category_ribbon =(MobileElement)(driver.findElement(By.name("intel")));
        category_ribbon.click();
        MobileElement home_button =(MobileElement)(driver.findElement(By.id("jp.co.necp.mytimeline:id/spoke_header_home_button")));
		home_button.click();
       
		
	}
	
	/*
	@Test(priority=2)
	public void search()
	{
		
		MobileElement srch =(MobileElement)(driver.findElement(By.id("jp.co.necp.mytimeline:id/header_search_button")));
		srch.click();
		MobileElement srch_input =(MobileElement)(driver.findElement(By.id("jp.co.necp.mytimeline:id/search_input")));
		srch_input.sendKeys("Lenovo");
		srch_input.sendKeys(Keys.TAB);
		srch_input.sendKeys(Keys.ENTER);
	MobileElement srch1 =(MobileElement)(driver.findElement(By.id("jp.co.necp.mytimeline:id/header_search_button")));
		srch1.click(); 
		System.out.println("Item Searched");
}
*/

	@AfterClass
	public void quit()  {
		System.out.println("Clicked");
	}
	
}


