package MTL_Methods_Archive;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


public class Methods_Archive {
	static AndroidDriver driver;
	
	public static void MTL_Setup() throws MalformedURLException
	{
		System.out.println("Ssetting desired capibilities");
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("no-reset", "true");
		cap.setCapability("full-reset", "false");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nec-pc_ts508fam-6b39b22");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
		cap.setCapability("version", "6.0.1");
		cap.setCapability("appPackage", "jp.co.necp.mytimeline");
		cap.setCapability("appActivity", "jp.co.necp.mytimeline.MainActivity");
	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	    try{Thread.sleep(9000);
		  }
		  catch(Exception e){} 
		//cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "90");

		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

}
