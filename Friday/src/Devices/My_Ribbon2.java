package Devices;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class My_Ribbon2 {
	
	AndroidDriver driver;
	
	
	//Capabilities of apk going to automate
	@BeforeTest
	 public void setup() throws MalformedURLException
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
	
	@Test(invocationCount=2)
	public void test() 
	{
		System.out.println("CLicking on settings icon");
		WebElement settings_icon = driver.findElement(By.id("jp.co.necp.mytimeline:id/header_setting_button"));
		settings_icon.click();
		WebElement My_ribbname = driver.findElement(By.name("マイリボン設定"));
		My_ribbname.click();
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement add_ribbon = driver.findElement(By.name("新規作成"));
		add_ribbon.click();
		// Enter name in my ribbon text field
		WebElement enter_ribbonName = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_add_keyword_text"));
		String ribbon_name="Intel";
		enter_ribbonName.sendKeys(ribbon_name);
		//click create ribbon button
		WebElement hit_createRibbon = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_keyword_add_button"));
		hit_createRibbon.click();
		WebElement created_ribbon=driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_text"));
		System.out.println(created_ribbon.getText());
		System.out.println("Verification of Created ribbon is successfull");
		
        assertEquals(ribbon_name, created_ribbon.getText());
		WebElement delete_ribbon=driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_delete_button"));
		delete_ribbon.click();
		System.out.println("Verification after deletion of created ribbon");
		WebElement ribbon_space =driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_keyword_list"));
		if(ribbon_space.getText()==ribbon_name){
			System.out.println("Ribbon is not deleted");
		}
		else{
			System.out.println("Ribbon is deleted successfully");
		}
		// click back button on ribbon creation window
		WebElement back_ribbon = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_back_btn"));
		back_ribbon.click();
		
		//clicking back button on ribbon screen
		back_ribbon.click();
		
		//clicking on settings icon to hide the settings window
		
		settings_icon.click();
		
		
	}
	
	@AfterTest
	public void close() throws InterruptedException
	{
		System.out.println("hello");
		driver.closeApp();
		//driver.resetApp();
    }
	
}

