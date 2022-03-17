package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import AppiumMavenGrpID.AppiumFramework.AndroidAPPBaseEcommerceApp;

//Listeners in TestNG listens to the test results and in case of failure, navigates the execution control to a block which captures screenshot. 
//Where test result is success, navigates the execution control to another block.
//To implement the Listener in TestNG, we have an interface called ITestListerner which has all the methods for TestNG Listeners.

// STEP 1 (Listener Implementation)
// Create Class called 'Listeners' (under the 'resources' package) and write 'implements ITestListener'
// When we hover over Listeners class, it does not suggest to "Add unimplemented methods". 
// It is because it is only enabled in TestNG version 6.14.3. Since we have TestNG version 7.4.0.202106051955, it is not suggested. 
// Therefore, following the below steps:
// Add package 'import org.testng.ITestListener'
// Select 'Add TestNG Library'
// Right-click (where u have to generate this interface) 
// Select 'Source'
// Select 'Override/Implement Methods 
// Select the check box for ITestListener (all check boxes inside ITestListener get selected automatically) 
// Select 'OK' button

public class Listeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
	}

	//STEP 2 (Listener Implementation)
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// ITestListener.super.onTestFailure(result);
		
		// Surround with try catch comes as a suggestion, just accept it
		try {
			
			// result object contains the details on the test which failed
			// result.getName() returns the test (method) name which failed
			// Lets make the failure file screenshot unique and give it the test name i.e. method name which failed
	   	    // Therefore, from this i.e. listeners class, when getScreenshot() method is called, send the test (method) name as an argument to getScreenshot() method in 'AndroidAPPBaseEcommerceApp' class
			String tname= result.getName();
			AndroidAPPBaseEcommerceApp.getScreenshot(tname);
			System.out.println("Just called getScreenshot method to take the failed page screenshot");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
