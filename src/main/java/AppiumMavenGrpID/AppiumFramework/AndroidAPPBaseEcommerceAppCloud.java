
// The objective of this test is to define Desired Capabilities when using browserstack cloud infrastructure

package AppiumMavenGrpID.AppiumFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidAPPBaseEcommerceAppCloud 
{
	
	public static AndroidDriver <AndroidElement> driver;
	
// Define a string variable appName in the capability method in the base class to catch the APP name sent from cloudcapabilities() method from the test case (EcommerceAppAutomation4Cloud class) 
public static AndroidDriver<AndroidElement> cloudcapabilities() throws IOException, InterruptedException
	
	{	
		DesiredCapabilities caps = new DesiredCapabilities();
		
		FileInputStream fil = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AppiumMavenGrpID\\AppiumFramework\\global.properties");
		Properties pro = new Properties();
		pro.load(fil);
		String DeviceName = (String) pro.get("devicename");
		String OS = (String) pro.get("OperatingSystem");
		
		// Specify device and os_version for testing
		// Specify the device and the operating system also on which we want to execute our test  
		// So, in short we are telling browserstack to go and invoke Google Pixel 3 device 
		// and install android operating system 9.0 on the device
		// and then go and deploy the APP (details below) on the device
		     
		   caps.setCapability("device", DeviceName);
		   caps.setCapability("os_version", OS);
		   
		// Set your access credentials (these values can be retrieved from global properties file instead of hard coding in this class)
	    // When we try to execute our tests on cloud, BrowserStack will ask for credentials for authentication, therefore the access credentials capabilities are included. 
		// BrowserStack reads these capabilities at run-time and authenticated us before executing the test 
		
		caps.setCapability("browserstack.user", "sachinhjibhakate_Ihxr5q");
	    caps.setCapability("browserstack.key", "KHPBBTuzfCn5DmZn4df9");
		 
	  //caps.setCapability("device", "Google Pixel 3");
	  //caps.setCapability("os_version", "9.0");
		
		// Set URL of the application under test 
		// Once the APP is successfully uploaded in cloud, browserstack generates & gives the path where the APP is placed in cloud. 
		// This is just like earlier we needed to provide the APP path which was in the framework. 
		// Same way now, we need to give the APP path in cloud to the desired capabilities in the base class.
	      
		caps.setCapability("app", "bs://1022b9015dd28b033c66316991178b2b76078c5b");

		// With we running our tests on browserstack cloud infrastructure, we can remove the below line
		// because browserstack automatically adds it on run-time (if it doesn't find the below)
		// but we can keep this line in the code
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		Thread.sleep(4000);
		
		// Earlier we are sending our driver with ALL the capabilities to Appium Server hosted on local at port 4723
		// But now as the Appium Server is hosted in cloud within browserstack, we need to give browserstack URL
		// So driver knows the address of Appium Server where driver needs to give all the details
		// Initialize the remote Webdriver using BrowserStack remote URL
	    // and desired capabilities defined above
	    
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), caps);
	    
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
        return driver;   
	}
      
}
