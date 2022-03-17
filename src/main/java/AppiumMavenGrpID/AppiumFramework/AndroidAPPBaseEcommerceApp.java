package AppiumMavenGrpID.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.ServerSocket;

//The objective of this test is to:
// Define Desired Capabilities, wrap those in the Desired Capabilities object
// Create Android Driver and provide desired capabilities object and appium server address to the driver
// This is the base class written for main class EcommerceAppAutomation

import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidAPPBaseEcommerceApp {
	
	// We need to initialize the driver at a class level, so we can access the driver in any method within the class
	// If we initialize the driver within any method, then we can only access the driver within that method and not outside that method
	// If we make the method (where we are calling the driver static) then we need make the driver static as well
	public static AndroidDriver <AndroidElement> driver;
	
	// We need to declare the service variable - return type will be AppiumDriverLocalService
	// We need to declare the service variable as static as we wil call it from the test case (which is static) using methodname.variable name i.e. 
	public static AppiumDriverLocalService service;
	
	// if we want to call the method inside or outside of the class without creating object for the class, declare the method as static
    // We need to call the 'startServer()' method from the test case i.e. from the 'EcommerceAppAutomation4' class, so declare the 'startServer()' method as static
	public static AppiumDriverLocalService startServer()
	{
		// Appium has provided a static class called 'AppiumDriverLocalService'. This class helps to start the Appium Server. 
		// We have a method called 'buildDefaultService()' within the 'AppiumDriverLocalService' class. This method helps to start the Appium Server. 
		//'service' is the variable returned by 'buildDefaultService()' and return type of 'service' variable is 'AppiumDriverLocalService'
		// service.start() will start the Appium Server
		// If we start Appium Server programmatically, the Appium Server logs are generated in the Console with Eclipse
		// and if we start Appium Server using command prompt, the Appium Server logs are generated in the command prompt
		// return service - returns the service variable to the test case, so that service.stop can be given at the end of the test case to stop the Appium Server
		
		//call the 'checkIfServerIsRunnning(int port)' method and provide the port number 
		// as Appium server runs at port 4723, provide port number as 4723
		// checkIfServerIsRunnning(4723) checks if the server is running at port 4723
		// if the server is running, it returns true else it returns false
		boolean flag=checkIfServerIsRunnning(4723);
		
		// if flag is returned true, then it means server is already running, so we don't need to start it again
		// therefore, negation is used 
		// if flag is returned false, then it means server is not running, so we need to start it again
		// so negation of false is true, so the if block will be executed which will start the Appium server
		if (!flag)
		{
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
		System.out.println("Appium Server Started");
		}
		return service;
	}
	
// Following 'checkIfServerIsRunnning' method checks if the Server is running 
// if the Server is running at the mentioned port, it returns true else returns false (so returns a boolean value)
// if we want to call the method inside or outside of the class without creating object for the class, declare the method as static
	public static boolean checkIfServerIsRunnning(int port) 
	{
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	// At the path 'C:\Users\SACHINJIBHAKATE\AppData\Local\Android\Sdk\emulator', there is a .exe file called 'emulator' which is responsible to invoke emulator. So if we have to go to emulator, we need to:
	// i) Launch command prompt & navigate to the above path - cd C:\Users\SACHINJIBHAKATE\AppData\Local\Android\Sdk\emulator
	// ii) Give command 'emulator -avd SachinAndroidEmulator' (where 'SachinAndroidEmulator' is the name of the emulator)
	// so the above command can by executed by a .bat file
	// so we need to create a bat file with the command above and execute the bat file from the base class (if we double click the bat file, it gets executed)
	// Create a resources folder at the path 'C:\Users\SACHINJIBHAKATE\eclipse-workspace\AppiumFramework\src\main\java' and place the bat file under the resources folder
	// Note: When saving thebat file choose extension as .bat e.g. 'startemulator.bat' and 'Save as type' should be 'Text Documents(*.txt)
	
	// The below method when called will execute the .bat file and invoke the emulator
	// if we want to call the method inside or outside of the class without creating object for the class, declare the method as static
	public static void startEmulator() throws IOException, InterruptedException
	{
		// on runtime, go and execute the file at the path specified
		// select exec(String command)
		//'System.getProperty("user.dir")' gives the current project path
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startemulator.bat");
		// Runtime.getRuntime().exec("C:\\Users\\SACHINJIBHAKATE\\eclipse-workspace\\AppiumFramework\\src\\main\\java\\resources\\startEmulator.bat");
	    // As emulator may some time to boot, given some wait time
		Thread.sleep(10000);
	}
	
// Step 3: Define a string variable appName in the capability method in the base class to catch the string parameter name. 
public static AndroidDriver<AndroidElement> ecapabilities(String appName) throws IOException, InterruptedException
	
	{	
		// TODO Auto-generated method stub
		
		//When automating web browsers on desktop, we need to provide 'browser' & 'URL' details
		// e.g. Launch "https://www.google.com" URL in 'chrome' browser
		
		//When automating in Mobile Apps, we need to provide the below details:
		// APP (each app has .apk file), Emulator i.e. Virtual device name, Appium Server address
		// where Appium Server is listening - in this case port 4723
		
		// DesiredCapabilities is class in Appium used to declare set of basic requirements
		//such as app name, device name, operating system etc. We need to wrap all the requirements 
		// in DesiredCapabilities class and send it with the driver.
		
		//Appium uses UIAutomator2 engine to automate the mobile apps on Android
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// GLOBAL PROPERTIES- Refer Details Below:
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// APP NAME
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// APP information (i.e. which APP to execute the test cases on) should come from the test cases, so we need to send a parameter from the test case to the capabilities method to state on which app the test case should be executed, because we might have different test cases using different APPS
	// Step 1: We need to define a global property for the APP name in the global properties file
    // Step 2: Send parameter name (String) on the APP details from the test case to the capabilities method (parameter name we will map to a value in the global properties file)
    // Step 3: Define a string variable in the capability method in the base class to catch the string parameter name. 
	// Step 4: Write a code in the base class (where capabilities method is defined) to:
	// 4a) read the global properties file (FileInputStream class object in java have the ability to read the files. Provide the path of the global properties  (right click on the global properties file and select Properties), so it can read the file) 
	// and then 4b) Retrieve the value for the parameter from the  global properties file (Properties class object can retrieve the values for the properties defined within the  global property files. To give the knowledge of the properties file location and also to given the ability to read the file, load the FileInputStream class object in the Properties class object and then using get(parameter name) method the Properties class, retrieve the APP name. APP should be located in the same package as the global property file.
	// Step 5: Pass the extracted property value i.e APP name where APP file details are given.
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// DEVICE NAME
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // So, overall, just mention the device name (emulator/virtual device or real device) as the value for property called 'device name' and all the test cases will run on that device. This way we can run the same set of test cases on emulators/virtual devices and real devices
	// Step 6: We need to define a global property for the device name in the global properties file
	// e.g. device name = SachinAndroidEmulator (emulator name in case of emulator) or device name = Android Device (in case of real device)
	// then Step 7: pro.get("SachinAndroidEmulator") which will retrieve the value of the property called 'device name'
	// Step 8: Pass the extracted property value i.e Device Name in the setCapability method
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	
	
	// Step 4: Write a code in the base class (where capabilities method is defined) to do the following:
	//4a) Read the global properties file (FileInputStream class object in java have the ability to read the files. Provide the path of the global properties file, so it can read the file)
	// then 4b) Retrieve the value for the parameter from the  global properties file (Properties class object can retrieve the values for the properties defined within the  global property files. To give the knowledge of the properties file location and also to given the ability to read the file, load the FileInputStream class object in the Properties class object and then using get(parameter name) method the Properties class, retrieve the APP name. APP should be located in the same package as the global property file.
	// System.getProperty("user.dir") - This gets the current project path, so we can replace the local machine project path "C:\\Users\\SACHINJIBHAKATE\\eclipse-workspace\\AppiumFramework" with System.getProperty("user.dir") - This helps if this test needs to be run in different machines.
	// In Java, if we have to replace certain part of the string with something else (like in this case), then divide the string into 2 strings, separate by + and then replace one of the string
	FileInputStream fil = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AppiumMavenGrpID\\AppiumFramework\\global.properties");
	
	//FileInputStream fil = new FileInputStream("C:\\Users\\SACHINJIBHAKATE\\eclipse-workspace\\AppiumFramework\\src\\main\\java\\AppiumMavenGrpID\\AppiumFramework\\global.properties");
		
	Properties pro = new Properties();
	
	// select load (InputStream inStream) which is InputStream class object
	pro.load(fil);
	
	// The below will extract the value i.e. 'General-Store.apk' defined for the property called 'GeneralStoreAPP'
	// Since the extracted value is a string value, it suggests to cast the argument pro.get(GeneralStoreAPP)) to String
	// select get(Object key)
	String APP = (String) pro.get("GeneralStoreAPP");
	
	System.out.println("APP Name is retrieved from global property file");
	
	// The below will extract the value i.e. 'SachinAndroidEmulator' defined for the property called 'device name'
	// Since the extracted value is a string value, it suggests to cast the argument pro.get("device name")) to String
	// select get(Object key)

   // Step 7: pro.get("devicename") which will retrieve the value of the property called 'device name'
   // global property name should not have any spaces else it returns null value
   // PROVIDE DEVICE NAME through MAVEN COMMAND - If we give the device name using Maven command, then comment the below line & remove the device name from global properties file (for more details on this approach, refer to point 19) from 'Appium Framework Notes.txt'
   String DeviceName = (String) pro.get("devicename");

    // System.out.println(DeviceName);
	
	// PROVIDE DEVICE NAME through MAVEN COMMAND - Give System.getProperty("DeviceName") in this base class, so it catches the device name given to "DeviceName" variable in runtime (when we execute maven command) and catches the device name in "DeviceName" variable
	// String DeviceName = System.getProperty("DeviceName");
	
	System.out.println(DeviceName);
    
		File parentfolder = new File ("src");
		// Step 5: Pass the extracted string variable 'APP' (which contains the APP name string value) where APP file details are given.
		File file = new File(parentfolder, APP);
		// File file = new File(parentfolder, "General-Store.apk");
		
		// In the global properties file, follow a naming convention to have word "Emulator" in the emulator/virtual device names
		// The condition below checks if we have "Emulator" in the device name and if yes then invokes that emulator
		// If its a real device, we dont need to invoke the emulator as it will be plugged in to the laptop and will be ready to use
		if(DeviceName.contains("Emulator"))
		{
		// call the startEmulator() method which will execute the .bat file and invoke the emulator
			System.out.println("Hi8");
		startEmulator();	
		Thread.sleep(10000);
		System.out.println("Hi9");
		}
	
		DesiredCapabilities cap = new DesiredCapabilities();
		
		// Step 8: Pass the extracted property value i.e Device Name in the setCapability method
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		
		
		//We need to include the emulator/virtual device name when we want to run the automation test on android virtual device/emulator
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "SachinAndroidEmulator");
		//We need to include the below when we want to run the automation test on real android device
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		cap.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
		
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		Thread.sleep(4000);
		
		System.out.println("Hi1");
		
		// While executing the below line, if we face the error below then we need to remove the Selenium dependency from pom.xml
		// Exception in thread "main" java.lang.NoClassDefFoundError: org/openqa/selenium/virtualauthenticator/HasVirtualAuthenticator
		AndroidDriver <AndroidElement>driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		// driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		System.out.println("Hi2");
		
		 // driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		 // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
        return driver;
        
	}

      //STEP 3 (Listener Implementation)
      // We need to make this method static, so we can call this method from any other class using 'class name.method name' without creating an object of this class
     // From the listeners class, when getScreenshot() method in this class is called, send the test (method) name as an argument to getScreenshot() method in this class - getScreenshot(test name)
      
     public static void getScreenshot(String tname) throws IOException
      
      {
    	 // Tell driver to take screenshot as File i.e. store in a File object
    	 // We need to transfer the screenshot from File object into the File within the framework or on the local 
    	 // We need to cast the driver to TakeScreeshots method, so driver will go into the mode of capturing images. 
    	 // Then Driver understands that I have to turn into screenshot capturing mode and then driver will take screenshots.
    	   File scrshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	   
    	   System.out.println("Stored the screenshot into a file object");
    	   
    	 // Copy file from the File object scrshot into the .png file (give the path where it should place the file)
    	 // Lets make the failure file screenshot unique and give it the test name i.e. method name which failed
    	 // Therefore, from the listeners class, when getScreenshot() method in this class is called, send the test (method) name as an argument to getScreenshot() method in this class - getScreenshot(test name)
    	    FileUtils.copyFile(scrshot,new File(System.getProperty("user.dir")+"\\"+tname+".png"));
    	      
    	   System.out.println("Copied the screenshot from a file object into a file");
       }


}
