package AppiumMavenGrpID.AppiumFramework;
// test case for execution on browserstack cloud

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.FormsPage;

// When the test case is executed, it calls the data provider (defined after @Test annotation) and executes the test case for number of iterations defined within the data provider class

public class EcommerceAppAutomation4Cloud extends AndroidAPPBaseEcommerceAppCloud
{
	
	AndroidDriver<AndroidElement> driver;
	
	@Test(dataProvider="InputName", dataProviderClass = TestData.class)
	
	public void TestNGmethod(String Name) throws IOException, InterruptedException {
	
	// We can send the APP name from run-time, send the APP name by specifying it as an argument in the capabilities method e.g. cloudcapabilities("GeneralStoreAPP")
	// Once the APP is successfully uploaded in cloud, browserstack generates & gives the path where the APP is placed in cloud. 
	// In the base class we can then add if loop and check the APP name sent from cloudcapabilities() method
	// and if there are 2 APPS, we will have 2 paths in browserstack cloud
	// so based on the APP name, inside the if else block, set up the app name capability accordingly
		AndroidDriver<AndroidElement> driver = cloudcapabilities();
		
        FormsPage frm = new FormsPage (driver);	
		
		Thread.sleep(4000);
		
		driver.findElementByXPath("//android.widget.EditText[@text='Enter name here']").sendKeys(Name);
		
		frm.radioButton.click();
		// Instead of directly calling the web element from the page object class, we can call the method as below within the page object class and return the element from the method
		// frm.getradioButton().click();
		//driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		
		driver.findElementByAndroidUIAutomator("text(\"Afghanistan\")").click();
		
		ScrollUtility scrl = new ScrollUtility(driver);
		scrl.ScrollToText("Argentina");
			
		driver.findElementByXPath("//android.widget.TextView[@text='Argentina']").click();
		
		driver.findElementByXPath("//android.widget.Button").click();
		
		Thread.sleep(4000);
	
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
	
	System.out.println("main branch");
        
}    
        
}

