package Demo;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class MyRibbonRegistration_Demo {
	
	AndroidDriver driver;
	
	
	//Capabilities of apk going to automate
	@BeforeTest
	 public void setup_RibbRegist() throws MalformedURLException	
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
	
	@Test(priority=0) 
	public void ribbon_Register() 
	{
System.out.println("CLicking on settings icon");
		WebElement settings_icon = driver.findElement(By.id("jp.co.necp.mytimeline:id/header_setting_button"));
		settings_icon.click();
		WebElement My_ribbname = driver.findElement(By.name("マイリボン設定"));
		My_ribbname.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
;
		WebDriverWait waitx = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
		waitx.until(ExpectedConditions.visibilityOfElementLocated(By.name("新規作成")));
		WebElement add_ribbon = driver.findElement(By.name("新規作成"));
		add_ribbon.click();
		// Enter name in my ribbon text field
		WebElement enter_ribbonName = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_add_keyword_text"));
System.out.println("Registering ribbon with name Intel");
		String ribbon_name="Intel";
		enter_ribbonName.sendKeys(ribbon_name);
		//click create ribbon button
		WebElement hit_createRibbon = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_keyword_add_button"));
		hit_createRibbon.click();
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/myribbon_text")));
		WebElement created_ribbon=driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_text"));
System.out.println("Created ribbon under ribbon list is : "+created_ribbon.getText());
System.out.println("Verification under ribbon registration screen");
        AssertJUnit.assertEquals(ribbon_name, created_ribbon.getText());
		
		
		WebElement final_submit = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_myribbon_update_button"));
		final_submit.click();
		//wait for myribbon_decide_button
		WebDriverWait wait1 = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/myribbon_decide_button")));
		WebElement myribbon_decide_button = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_decide_button"));
		myribbon_decide_button.click();
System.out.println("Verification under ribbon Created list screen");		
		AssertJUnit.assertEquals(ribbon_name, created_ribbon.getText());
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
	
	
/*	@AfterTest
	public void close() 
	{
	//	System.out.println("hello");
		driver.closeApp();
		
	
    }

*/

}
