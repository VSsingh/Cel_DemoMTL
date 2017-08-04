package Devices;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

import java.awt.Button;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;
import com.sun.glass.events.KeyEvent;
import com.sun.glass.ui.View;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class My_Ribbon3 {
	
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
	
	@Test 
	public void test() throws InterruptedException, TimeoutException
	{
		System.out.println("CLicking on settings icon");
		WebElement settings_icon = driver.findElement(By.id("jp.co.necp.mytimeline:id/header_setting_button"));
		settings_icon.click();
		WebElement My_ribbname = driver.findElement(By.name("マイリボン設定"));
		My_ribbname.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement add_ribbon = driver.findElement(By.name("新規作成"));
		add_ribbon.click();
		
		//wait untill add ribbon test appears


		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/myribbon_setting_add_keyword_text")));
		// Enter name in my ribbon text field
		WebElement enter_ribbonName = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_add_keyword_text"));
		String ribbon_name="Intel";
		enter_ribbonName.sendKeys(ribbon_name);
		//click create ribbon button
		WebElement hit_createRibbon = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_keyword_add_button"));
		hit_createRibbon.click();
		WebElement created_ribbon=driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_text"));
		System.out.println(created_ribbon.getText());
		assertEquals(ribbon_name, created_ribbon.getText());
		WebElement final_submit = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_setting_myribbon_update_button"));
		final_submit.click();
		//wait for myribbon_decide_button
		WebDriverWait wait1 = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/myribbon_decide_button")));
		WebElement myribbon_decide_button = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_decide_button"));
		myribbon_decide_button.click();
		
		//Click on settings icon to hide settings window
		settings_icon.click();
		
		//Click on hamburger menu
		WebElement top_header_ribbon_button = driver.findElement(By.id("jp.co.necp.mytimeline:id/top_header_ribbon_button"));
		top_header_ribbon_button.click();
		
		while(driver.findElements(By.name(ribbon_name)).size()==0)
		{
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0,scrollStart,0,scrollEnd,2000);
		}
	
		if(driver.findElements(By.name(ribbon_name)).size()>0)
		{
			driver.findElement(By.name(ribbon_name)).click();
		}   
		
		WebDriverWait wait3=(WebDriverWait) new WebDriverWait(driver, 20).ignoring(TimeoutException.class,NoSuchElementException.class);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/spoke_header_home_button")));
		  System.out.println("Ribbon name verified");
		
	driver.pressKeyCode(AndroidKeyCode.BACK);
		
		

		
	  WebDriverWait wait4=(WebDriverWait) new WebDriverWait(driver, 10);
    	wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/header_search_button")));   
		// going to delete created ribbon
		
	/*	
		My_ribbname.click();
		
		WebDriverWait wait2 =new WebDriverWait(driver,10);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("新規作成")));
		
		WebElement myribbon_delete_mode_button = driver.findElement(By.id("jp.co.necp.mytimeline:id/myribbon_delete_mode_button"));
		myribbon_delete_mode_button.click();
		WebElement myribbon1_delete_mode_button = driver.findElement(By.name("削除"));
		myribbon1_delete_mode_button.click();
		
		WebElement blank_ribbon_page = driver.findElement(By.name("登録済みのマイリボンを編集"));
		System.out.println(blank_ribbon_page.getText());
		System.out.println("Created ribbon is deleted");
				
		}
	
*/
		  
	}		  
	
	

	@AfterTest
	public void close() throws InterruptedException
	{
		System.out.println("hello");
	
		 driver.closeApp();
	        driver.quit();
	        driver = null;
	
    }
	
}

