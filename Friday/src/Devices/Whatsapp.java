package Devices;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


public class Whatsapp {
	AndroidDriver driver;
	
	@BeforeClass
	public void setup() throws MalformedURLException{
		DesiredCapabilities capabilty=new DesiredCapabilities();
		
		capabilty.setCapability("no-reset", "true");
		capabilty.setCapability("full-reset", "False");
		capabilty.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilty.setCapability(MobileCapabilityType.DEVICE_NAME, "xiaomi-2014818-6e4ce18d7d62");
		capabilty.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
		
		
     //  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	//	capabilty.setCapability("DeviceName", "xiaomi-2014818-6e4ce18d7d62");.
		
		capabilty.setCapability("version", "5.1.1");
	//	capabilty.setCapability("PlatformName", "Android");
			
		capabilty.setCapability("appPackage","com.whatsapp");
		
		capabilty.setCapability("appActivity", "com.whatsapp.Main");
		
		// Initialize the driver object with the URL to Appium Server and pass capabilities
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilty);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(priority=0)
	public void launch()throws InterruptedException{
		System.out.println("Launching whatsapp");
		
		MobileElement Status =(MobileElement)(driver.findElement(By.name("STATUS")));
		Status.click();
		
		
		
		MobileElement calls =(MobileElement)(driver.findElement(By.name("CALLS")));
		calls.click();
		
		
	}
	
	@Test(priority=1)
	public void contacts() throws InterruptedException
	{
		MobileElement contact =(MobileElement)(driver.findElementByAccessibilityId("New call"));
		contact.click();
		
	}
	
	
	@AfterClass
	public void quit(){
	//	driver.close();
	}
	
}
