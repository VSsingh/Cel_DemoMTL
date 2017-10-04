package Demo;

import org.testng.annotations.Test;
import MTL_Archive.Repository_Archive;
import org.testng.Assert;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Search1 {
	
AndroidDriver driver;

//MTL app launching method
@BeforeTest
public void Setup() throws MalformedURLException
{
	DesiredCapabilities cap = new DesiredCapabilities();
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

// search keyword & compare 
@Test
public void search1() throws TimeoutException,InterruptedException
{
System.out.println("Clicking on Search magnifying glass");
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      WebElement searchIcon =driver.findElement(By.id(Repository_Archive.SearchButton_id));   // click on search icon
      searchIcon.click();
      WebDriverWait waitx = (WebDriverWait) new WebDriverWait(driver,10).ignoring(TimeoutException.class,NoSuchElementException.class);
	  waitx.until(ExpectedConditions.visibilityOfElementLocated(By.id(Repository_Archive.SearchFieldInput_id)));
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      WebElement searchField =driver.findElement(By.id(Repository_Archive.SearchFieldInput_id));
      searchField.sendKeys(Repository_Archive.SearchString_name);             // Type keyword in search inputbox
      driver.hideKeyboard();
      WebElement search =driver.findElement(By.xpath(Repository_Archive.SearchButton_xpath));
      search.click();                                                         // click on search icon
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      WebElement registerribbon = driver.findElement(By.id(Repository_Archive.SearchSettingIcon_id));
      registerribbon.click();                                                
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      WebElement foundkeyword =driver.findElement(By.id(Repository_Archive.SearchedText_id));
      String ribbonValue =foundkeyword.getText();
      Assert.assertEquals(ribbonValue,Repository_Archive.SearchString_name);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
      WebElement searchconditionbutton = driver.findElement(By.id(Repository_Archive.SearchConditionButton_id));
      System.out.println("Search condition button text is : "+searchconditionbutton.getText() );
}

//Method to close the launched MTL app from foreground	
 @AfterTest
public void closeddriver() throws InterruptedException
{
	 System.out.println("Close driver");
	 driver.quit();
	
}
}
