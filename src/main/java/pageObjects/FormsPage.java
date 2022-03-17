package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import AppiumMavenGrpID.AppiumFramework.AndroidAPPBaseEcommerceAppCloud;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormsPage {
	
	// below method is the constructor for class FormsPage
	// constructor is called when an object of the class is created
	// constructor can catch value
	// Use the method initElements(FieldDecorator locator, Object page)
		
	// Step 1 - locate the element on the page
	// Line 31 below is equivalent to driver.findElementById("com.androidsample.generalstore:id/radioFemale")
	// i.e. Appium converts the below line to driver.findElementById("com.androidsample.generalstore:id/radioFemale")
	// For iOS, use method @iOSBy
	
	public FormsPage(AndroidDriver <AndroidElement> driver)
	{
		// Step 2 - concatenate all elements defined on this page with the driver
		// when line 33 is executed, Appium concatenate all elements defined on this page with the driver
		// this stands for this page
		// so basically it does driver.findElementById("com.androidsample.generalstore:id/radioFemale")
		// For mobile devices e.g. android, we need to use this format new AppiumFieldDecorator(driver). This is not required in Selenium

		System.out.println("Control in the constructor method");
		
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		
		System.out.println("Finished executing the constructor method");
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	public WebElement radioButton;
	
	// If we use the method below to return the element to the test case i.e. EcommerceAppAutomation4Cloud.class, then make the web element above as private i.e. public WebElement radioButton, so it can't be called directly outside of this class 
	//  public WebElement getradioButton()
	//{
	//  System.out.println("Returning radioButton element now");
	//  return radioButton;
	//}
	
	//driver.findElementById("com.androidsample.generalstore:id/radioFemale")
	
}

// Then Step 3 would be just to click the WebElement radioButton
// From the test case, we will call the WebElement radioButton using frm.radioButton and then just click on it 
