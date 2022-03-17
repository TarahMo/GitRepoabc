package AppiumMavenGrpID.AppiumFramework;

//test case for execution on browserstack cloud

import java.io.IOException;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

// When the test case is executed, it calls the data provider (defined after @Test annotation) and executes the test case for number of iterations defined within the data provider class

public class EcommerceAppAutomation4CloudPartial extends AndroidAPPBaseEcommerceAppCloud
{
	
	AndroidDriver<AndroidElement> driver;
	
	@Test(dataProvider="InputName", dataProviderClass = TestData.class)
	
	public void TestNGmethod(String Name) throws IOException, InterruptedException 
	
	{
	
	// We can send the APP name from run-time, send the APP name by specifying it as an argument in the capabilities method e.g. cloudcapabilities("GeneralStoreAPP")
	// Once the APP is successfully uploaded in cloud, browserstack generates & gives the path where the APP is placed in cloud. 
	// In the base class we can then add if loop and check the APP name sent from cloudcapabilities() method
	// and if there are 2 APPS, we will have 2 paths in browserstack cloud
	// so based on the APP name, inside the if else block, set up the app name capability accordingly
		AndroidDriver<AndroidElement> driver = cloudcapabilities();
		
		Thread.sleep(4000);
		
		driver.findElementByXPath("//android.widget.EditText[@text='Enter name here']").sendKeys(Name);
		
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		driver.findElementByAndroidUIAutomator("text(\"Afghanistan\")").click();
		
		ScrollUtility scrl = new ScrollUtility(driver);
		scrl.ScrollToText("Argentina");
			
		driver.findElementByXPath("//android.widget.TextView[@text='Argentina']").click();
		
		driver.findElementByXPath("//android.widget.Button").click();
				        
     }    
        
}

