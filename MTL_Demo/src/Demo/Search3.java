package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MTL_Archive.Repository_Archive;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Search3 {
	
AndroidDriver driver;

//MTL app launching method
@BeforeTest
public void Setup() throws MalformedURLException
{
	DesiredCapabilities cap =new DesiredCapabilities();
	cap.setCapability("No-reset", true);
	cap.setCapability("full-reset", false);
	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508fam-6b3f9b22");
	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
	cap.setCapability("version", "6.0.1");
	cap.setCapability("appPackage", "jp.co.necp.mytimeline");
	cap.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
	driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
}

// Verification of searched keyword under display ribbon screen
@Test
public void search2() throws InterruptedException,TimeoutException
{

System.out.println("Clicking on Search magnifying glass");
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      WebElement searchIcon =driver.findElement(By.id(Repository_Archive.SearchButton_id)); 
      searchIcon.click();  // click on search
      WebDriverWait waitx = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
	  waitx.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.SearchFieldInput_id)));
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      WebElement searchField =driver.findElement(By.id(Repository_Archive.SearchFieldInput_id)); // click on search text field
      searchField.sendKeys(Repository_Archive.SearchString_name);
      driver.hideKeyboard(); // hide keyboard
      WebElement search =driver.findElement(By.xpath(Repository_Archive.SearchButton_xpath));
      search.click();    // click on search again
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      WebElement registerribbon = driver.findElement(By.id(Repository_Archive.SearchSettingIcon_id));
      registerribbon.click();  // Register keyword to my ribbon
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      WebElement foundkeyword =driver.findElement(By.id(Repository_Archive.SearchedText_id));
      String ribbonValue =foundkeyword.getText();
      Assert.assertEquals(ribbonValue,Repository_Archive.SearchString_name); // compare searched keyword with registered ribbon keyword
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
      driver.findElement(By.id(Repository_Archive.RibbonRegisterbutton_id)).click(); // Register searched keyword
      WebElement DisplayRibbon = driver.findElement(By.id(Repository_Archive.DisplayRibbonScreen_id));
      String DisplayRibb = DisplayRibbon.getText();
System.out.println("Searched keyword is: "+ Repository_Archive.SearchString_name);  
System.out.println("Created ribbon under register screen: "+ ribbonValue );
System.out.println("Searched keyword under ribbon didplay screen : "+DisplayRibbon.getText());
   
   
     
      
}      
 
//Method to close the launched MTL app from foreground	
@AfterTest
public void closeDriver()
{
	driver.quit();
}


}
