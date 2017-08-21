package Demo;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


public class shareMenu_ListArticles {
 AndroidDriver driver;
  
  
  //Capabilities of apk going to automate
  @BeforeClass
  
   public void setup() throws MalformedURLException
   
   {
   
   System.out.println("Setting desired capibilities");
   DesiredCapabilities cap=new DesiredCapabilities();
   cap.setCapability("no-reset", "true");
   cap.setCapability("full-reset", "false");
   cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
   cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508baw-LenovoTAB2AB");
   cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
   cap.setCapability("version", "5.0.2");
   
   cap.setCapability("appPackage", "jp.co.necp.mytimeline");
   cap.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
   driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
   
   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 //  Locale dLocale = new Locale.Builder().setLanguage("ja").setScript("Cyrl").build();

   }
  
  
  @Test
  public void test() throws Exception
  {
	/*  Locale enLocale = new Locale("ja", "JA");  
	  System.out.println("English language name (default): " +   
              enLocale.getDisplayLanguage());  */
    System.out.println("Hello");
   WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 10).ignoring(NoSuchElementException.class);
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jp.co.necp.mytimeline:id/big_tile")));
   WebElement a= driver.findElement(By.id("jp.co.necp.mytimeline:id/big_tile")) ;
   WebElement w=a.findElement(By.id("jp.co.necp.mytimeline:id/article_title"));
   System.out.println(w.getText());
   String a1 = w.getText();
   w.click();
   WebElement b=driver.findElement(By.id("jp.co.necp.mytimeline:id/detail_title"));
   String a2=b.getText();
   System.out.println(b.getText());
   AssertJUnit.assertEquals(a1,a2);
   System.out.println("String verified successsfully");
   
  
   
  System.out.println("Detail Screen is verified ");
     

   //   driver.pressKeyCode(AndroidKeyCode.BACK);
   System.out.println("Application back key is pressed");
  }
   
  
/*  @AfterTest
  public void close() throws InterruptedException
  {
   System.out.println("hello");
   driver.closeApp();
   //driver.resetApp();
     }
*/  

 }