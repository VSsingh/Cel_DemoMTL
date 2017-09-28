package Demo;

import org.testng.annotations.Test;
import MTL_Archive.Repository_Archive;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class MyRibbonRegistration_Demo {
	
	AndroidDriver driver;
	
	
// MTL app launching method
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	 }
	
	@Test
	public void ribbon_Register() throws TimeoutException,NoSuchElementException
	{
System.out.println("Clicking on settings icon");
		WebElement settings_icon = driver.findElement(By.id(Repository_Archive.settingIcon_id));
		settings_icon.click();
		WebElement My_ribbname = driver.findElement(By.id(Repository_Archive.ribbonIcon_id));
		My_ribbname.click();

		
/*	WebDriverWait wait1 = (WebDriverWait) new WebDriverWait(driver,30);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.RibbonAddButton_id))); */
		WebElement add_ribbon = driver.findElement(By.id(Repository_Archive.RibbonAddButton_id));
		add_ribbon.click();

// Enter name in my ribbon text field
/*	WebDriverWait waitx = (WebDriverWait) new WebDriverWait(driver,30);
		waitx.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.RibbonTextField_id))); */
		
		WebElement enter_ribbonName = driver.findElement(By.id(Repository_Archive.RibbonTextField_id));

		enter_ribbonName.click();

System.out.println("Registering ribbon");
	//	String ribbon_name=Repository_Archive.UserRibbon_name;
		enter_ribbonName.sendKeys(Repository_Archive.UserRibbon_name);
		driver.hideKeyboard();
//click create ribbon button
		  try{Thread.sleep(2000);
		  }
		  catch(Exception e){} 
		WebElement hit_createRibbon = driver.findElement(By.id(Repository_Archive.RibbonAddTobutton_id));
		hit_createRibbon.click();

/*		WebDriverWait wait2 = (WebDriverWait) new WebDriverWait(driver,30);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.RibbonRegisterbutton_id))); */
		WebElement created_ribbon=driver.findElement(By.xpath(Repository_Archive.CreatedRibbon_xpath));
System.out.println("Created ribbon under ribbon list is : "+created_ribbon.getText());
System.out.println("Verification under ribbon registration screen");
   //     Assert.assertEquals(ribbon_name, created_ribbon.getText());
		
		
		WebElement final_submit = driver.findElement(By.id(Repository_Archive.RibbonRegisterbutton_id));
		final_submit.click();

//wait for myribbon_decide_button
/*		WebDriverWait wait3 = (WebDriverWait) new WebDriverWait(driver,30);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.RibbonDecidebutton_id))); */
		WebElement myribbon_decide_button = driver.findElement(By.id(Repository_Archive.RibbonDecidebutton_id));
		myribbon_decide_button.click();

System.out.println("Verification under ribbon Created list screen");		
	//	Assert.assertEquals(ribbon_name, created_ribbon.getText());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
	
/*	@AfterTest
	public void close() 
	{
		
		System.out.println("hello");
		driver.closeApp();
		
	
    }
*/

}
