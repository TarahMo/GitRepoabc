package AppiumMavenGrpID.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	
	// We need to give the name to the data provider
	@DataProvider(name="InputName")
	public Object[][] getData()
	
	{
	// Object is the superclass for all the data types
	// so, if we are not sure about the data type of the elements within the array i.e. if they are integer, string or double, then use Object
	// Define multidimensional array - 2 rows and 1 column
	// e.g. if we have test case where we need test the 'Name' field with different name values including special characters
		
     Object[][] data = new Object[2][1];
		
		data[0][0]="hello";
		data[1][0]="##$$@@";	
		
		return data;
	}
}
