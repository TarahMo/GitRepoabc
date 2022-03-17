package AppiumMavenGrpID.AppiumFramework;
//The objective of this test is to showcase how to automate Hybrid APP, how to extract contexts from the APP, how to automate the Web View

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.FormsPage;

// When the test case is executed, it calls the data provider (defined after @Test annotation) and executes the test case for number of iterations defined within the data provider class

public class EcommerceAppAutomation4 extends AndroidAPPBaseEcommerceApp{
	
	// Convert the test case to TestNG:
	// Add @Test annotation by creating a method in 'EcommerceAppAutomation4' class (replace the main () method with a method with @Test annotation)

	//public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
	
	// We need to write method with @BeforeTest annotation outside of the method with @Test annotation.
	// Classes are written within the 'test' tags in testng.xml file 
	// Therefore, if we write a method with @BeforeTest annotation within any of those classes, then that method is executed before the execution for those classes
	// Appium can be started externally i.e. from a command prompt using 'Appium' command and from the framework using 'service.start()' 
	// In a scenario where we start Appium externally, flag will return true
	// and then if block will initializes'service' and then starts server will not be executed
	// So, in this scenario when 'service' variable is returned and 'service.stop()' (line 146) is executed (which is executed at the end of each test)
	// then NullPointerException is displayed as 'service' variable does not exist
	// Therefore, we need to stop any sever running on any node (invoked externally' before the test is executed
	// Therefore 'Runtime.getRuntime().exec("taskkill /F /IM node.exe")' is included in a method with @BeforeTest annotation
	
	// Following code is not for this class. It is from 'AndroidAPPBaseEcommerceApp' class. 
	// It is mentioned in this class to explain the above lines 24 to 33)
	
	// boolean flag=checkIfServerIsRunnning(4723);
	// if (!flag)
	// {
	// service=AppiumDriverLocalService.buildDefaultService();
	// service.start();
	// System.out.println("Appium Server Started");
	// }
	// return service; 
	
	@BeforeTest
	public void stopserversrunningatnodes () throws IOException, InterruptedException
	{
	// If we have commands to execute from the command prompt, we can include them in a bat file & execute the bat file using exec method below and provide the path of the bat file within the exec method
	// However, if we just have one command to be executed from the command prompt, then we can directly give the command within th exec method
	// Select 'exec (String command)' when choosing the exec method after typing .exec
	   Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	   System.out.println("This block is executed to stop any running servers invoked externally (if at all they were running)");
	   Thread.sleep(3000);
	}
		
	// Call the data provider from this test case 
	// Since the data provider is located within a different class 'TestData' class (and not in this class), we need to provide the data provider class name as well 
	@Test(dataProvider="InputName", dataProviderClass = TestData.class)
	
	// We need to catch the variable (sent from 'TestData' class) in the method (defined with @Test) arguments in this test case 
	// 'Name' variable will catch the variable (sent from 'TestData' class)
	// if there are 2 variables in one iteration in one row e.g. [0][0] & [0][1] send by data provider class, we need catch 2 variables as the method arguments
	public void TestNGmethod(String Name) throws IOException, InterruptedException {
	
		// This should be the first step in the test case as this step will start the Appium Server
		// As startServer() method is a static method & as this class is extending the base class 'AndroidAPPBaseEcommerceAp' which contains the startServer() method, we can directly call the This class is extending the base class 'AndroidAPPBaseEcommerceAp' which contains the startServer() method in this class
		// startServer() method returns the service variable.
		// We need the service variable to stop the Appium Server at the end of each test case
		service = startServer();
		
		// Call capabilities() method 
		// Step 2: Send parameter name (String) e.g. "GeneralStoreApp" (it can be anything, since its a general store app, we mentioned parameter name as "GeneralStoreApp") on the APP details from the test case to the capabilities method (parameter name we will map to a value in the global properties file)
		AndroidDriver<AndroidElement> driver = ecapabilities("GeneralStoreAPP");
		
		Thread.sleep(4000);
		
		//  We need to catch the variable sent from data provider class into the method in this class
		// and then we need to catch that variable in the below step where data is populated in the field
		driver.findElementByXPath("//android.widget.EditText[@text='Enter name here']").sendKeys(Name);
		
		//The below step is to hide the keyboard if required during the test 
		//driver.hideKeyboard();
		
		//driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		//Below 2 lines replaces the above line & contains the code for page object model
		FormsPage fPage = new FormsPage(driver);
		fPage.radioButton.click();
		
		driver.findElementByAndroidUIAutomator("text(\"Afghanistan\")").click();
		
		// We need to create object of class 'ScrollUtility' so that constructor of class 'ScrollUtility' is called 
		// This is because we need to send the driver of this class to 'ScrollUtility' class
		// and then assign driver from this class (which has all the details including capabilities etc.) to 'this.driver' i.e. to the driver of 'ScrollUtility' class to give all the details including capabilities etc. to the driver of 'ScrollUtility' class
		ScrollUtility scrl = new ScrollUtility(driver);
		
		//scrolling action (until the text provided is reached) will be carried out in 'ScrollUtility' class
		// and then click on that text will be carried out in this class
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		// replace the above step i.e. step 48 with the below step - pass the text (text until which scrolling is required)
		scrl.ScrollToText("Argentina");
			
		driver.findElementByXPath("//android.widget.TextView[@text='Argentina']").click();
		//driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		//find element with tag 'android.widget.Button'. Since there is only button on the page, there is only one element with tag name 'android.widget.Button' and therefore the element can be located.
		//In the XPath syntax, [@attribute='value'] is optional
		driver.findElementByXPath("//android.widget.Button").click();
		
		 System.out.println("test");
		
		//Thread.sleep() is very important. Always add it when actions on the current page are complete & before the control moves to the next page
		//If Thread.sleep is used then StaleElementReferenceException can be avoided
		
		Thread.sleep(4000);

		//Retrieve the size i.e. number of visible elements on the page, loop through each element & click the element
		
		int numberofelements = driver.findElementsById("com.androidsample.generalstore:id/productAddCart").size();
        System.out.println(numberofelements);

        for (int i=0;i<numberofelements;i++)
        {
        	driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
     
        }
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
        
        Thread.sleep(4000);
		
        // Retrieve the size i.e. number of elements on the page with the same id, loop through each element and sum
        int bnumberofelements = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
        System.out.println(bnumberofelements);
        
        double sum = 0.0;
        
        for (int j=0;j<bnumberofelements;j++)
        {
        	String productvalue = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(j).getText();
        	// the below step is to remove $ from $160.97 - substring(1) means substring from the 1st index
        	productvalue = productvalue.substring(1);
        	// the below step is to convert string into double
        	double pvalue = Double.parseDouble(productvalue);
        	sum = sum + pvalue;
        	System.out.println(sum);
        }    
        
        Thread.sleep(4000);
        
        String finalValue = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
        finalValue = finalValue.substring(1);
        double qvalue = Double.parseDouble(finalValue);
        System.out.println(qvalue);
        
        Assert.assertEquals(qvalue, sum);
        
        // This should be the last step in each test case as this step will stop the Appium Server
        service.stop();
}    
        
}

