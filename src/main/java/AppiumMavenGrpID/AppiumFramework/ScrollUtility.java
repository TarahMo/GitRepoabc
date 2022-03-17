package AppiumMavenGrpID.AppiumFramework;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ScrollUtility {
	
	// This is local class driver but this driver does not have a life
	AndroidDriver<AndroidElement> driver;
	
	//driver from the class 'EcommerceAppAutomation4' is catched below 
	// below constructor method is called when object of this class is defined in 'EcommerceAppAutomation4' class
	public ScrollUtility (AndroidDriver<AndroidElement> driver)
	
	{
		// so we are assigning driver from class 'EcommerceAppAutomation4' (which has all the details including capabilities etc.) to 'this.driver' i.e. to the driver of this call to give all the details including capabilities etc. to the driver of this class
		//because the scrolling action (until the text provided is reached) will be carried out in this class only
		// and then click on that text will be carried out in 'EcommerceAppAutomation4' class
		this.driver=driver;
	}
	// We just need to call the below method from the 'EcommerceAppAutomation4' class and send the text which will be catched below in the 'text' variable
	public void ScrollToText(String text)
	{
	  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}

}
