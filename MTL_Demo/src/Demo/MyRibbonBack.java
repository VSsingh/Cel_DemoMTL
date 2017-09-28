//Prerequisite all ribbons must be deleted first

package Demo;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MTL_Archive.Repository_Archive;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class MyRibbonBack {
	
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
	
	@Test(priority=0)
	public void ribbon_Register() 
	{
System.out.println("CLicking on settings icon");
		WebElement settings_icon = driver.findElement(By.id(Repository_Archive.settingIcon_id));
		settings_icon.click();
		WebElement My_ribbname = driver.findElement(By.name(Repository_Archive.ribbonIcon_name));
		My_ribbname.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
System.out.println("Hault");
		WebDriverWait waitx = (WebDriverWait) new WebDriverWait(driver,10);
		waitx.until(ExpectedConditions.visibilityOfElementLocated(By.name("新規作成")));
		WebElement add_ribbon = driver.findElement(By.name(Repository_Archive.RibbonAddButton_name));
		add_ribbon.click();
		// Enter name in my ribbon text field
		WebElement enter_ribbonName = driver.findElement(By.id(Repository_Archive.RibbonTextField_id));
//System.out.println("Registering ribbon with name :"+enter_ribbonName.getText());
		enter_ribbonName.sendKeys(Repository_Archive.UserRibbon_name);
		//click create ribbon button
		WebElement hit_createRibbon = driver.findElement(By.id(Repository_Archive.RibbonAddTobutton_id));
		hit_createRibbon.click();
		WebElement created_ribbon=driver.findElement(By.name(Repository_Archive.UserRibbon_name));
		System.out.println("Created ribbon under ribbon list is : "+created_ribbon.getText());
System.out.println("Verification under ribbon registration screen");
        
// Comparison between inserted ribbon name and created ribbon name
        Assert.assertEquals(Repository_Archive.UserRibbon_name, created_ribbon.getText());
		System.out.println("passed");
		
		WebElement final_submit = driver.findElement(By.id(Repository_Archive.RibbonRegisterbutton_id));
		final_submit.click();
		//wait for myribbon_decide_button
		WebDriverWait wait1 = (WebDriverWait) new WebDriverWait(driver,10);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.RibbonDecidebutton_id)));
		WebElement myribbon_decide_button = driver.findElement(By.id(Repository_Archive.RibbonDecidebutton_id));
		myribbon_decide_button.click();
System.out.println("Verification under ribbon Created list screen");	

//Comparison between inserted ribbon name and created ribbon name     
        
		Assert.assertEquals(Repository_Archive.UserRibbon_name, created_ribbon.getText());
		}
// Verification of created my ribbon under hamburger menu	
		@Test(priority=1)
		public void hamburger_menu()
		{
			WebElement settings_icon1 = driver.findElement(By.id(Repository_Archive.settingIcon_id));
			settings_icon1.click();
			WebElement hamburger_icon = driver.findElement(By.id(Repository_Archive.HamburgerMenu_id));
			hamburger_icon.click();
			
			while(driver.findElements(By.name(Repository_Archive.UserRibbon_name)).size()==0)
			{
				Dimension dimensions = driver.manage().window().getSize();
				Double screenHeightStart = dimensions.getHeight() * 0.5;
				int scrollStart = screenHeightStart.intValue();
				Double screenHeightEnd = dimensions.getHeight() * 0.2;
				int scrollEnd = screenHeightEnd.intValue();
				driver.swipe(0,scrollStart,0,scrollEnd,2000);
			}
		
			if(driver.findElements(By.name(Repository_Archive.UserRibbon_name)).size()>0)
			{
				driver.findElement(By.name(Repository_Archive.UserRibbon_name)).click();
			}   
			WebDriverWait wait4=(WebDriverWait) new WebDriverWait(driver, 20);

			wait4.until(ExpectedConditions.presenceOfElementLocated(By.id(Repository_Archive.HomeIconId_id)));
			  try{Thread.sleep(9000);
			  }
			  catch(Exception e){} 
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
		}
				
// delete created ribbon
	/*	
       @Test(priority=3)
       public void ribbon_delete()
       {
    	   WebElement settings_icon1 = driver.findElement(By.id(Repository.settingIcon_id));
			settings_icon1.click();  
			WebElement My_ribbname = driver.findElement(By.name(Repository.ribbonIcon_name));
			My_ribbname.click();
    	    WebElement e1 = driver.findElement(By.id(Repository.ribbonDeleteIcon_id));
    	    e1.click();
    	  
    	   WebDriverWait wait4 = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
   		   wait4.until(ExpectedConditions.visibilityOfElementLocated(By.name(Repository.ConfrimDeleteIcon_name)));
    	   
    	   WebElement e3 = driver.findElement(By.name(Repository.ConfrimDeleteIcon_name));
    	   e3.click();   // click on final delete icon
    	   
       }
	*/
/*	@AfterTest
	public void close() throws InterruptedException
	{
		System.out.println("hello");
		driver.closeApp();
		//driver.resetApp();
    }

*/	

}
