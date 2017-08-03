package Demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.Scrollable;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.touch.ScrollAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class SpokeBack_Landscape {
	
	AndroidDriver driver;
	
	
	//Capabilities of apk going to automate
	@BeforeClass
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
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
	 }
	
	@Test
	public void test_Spoke() throws TimeoutException,InterruptedException
	{
		
		
		MobileElement hamburger_button =(MobileElement)(driver.findElement(By.id("jp.co.necp.mytimeline:id/top_header_ribbon_button")));
		hamburger_button.click();
		
	//	 String txt = hamburger_button.getText();
		WebDriverWait waitx = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
		waitx.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/ribbon_list_view")));
		WebElement driver1 = driver.findElement(By.id("jp.co.necp.mytimeline:id/ribbon_list_view"));

		while(driver1.findElements(By.name("国際")).size()==0)
		{
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0,scrollStart,0,scrollEnd,2000);
		}
		
		if(driver.findElements(By.name("国際")).size()>0)
		{
			driver.findElement(By.name("国際")).click();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}   

	
		System.out.println("Press android back key");
		((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.BACK);
		WebDriverWait wait = new WebDriverWait(driver,15);
		
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView[contains(@resource-id,'weather_icon')]")));
		WebElement weather_icon=driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'weather_icon')]"));
		
		 System.out.println("Weather existence under homepage is verified");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private void Scrollable(String string, String id) {
		// TODO Auto-generated method stub
		
	}
		
/*	@AfterClass
	public void close() {
	
		System.out.println("hello");
		driver.closeApp();
		
		//driver.resetApp();
    }
*/	

}
