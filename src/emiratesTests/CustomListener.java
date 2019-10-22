package emiratesTests;

import org.testng.ITestListener;
import org.testng.ITestResult;

import emiratesPage.SearchFlight;

public class CustomListener extends SearchFlight implements ITestListener{	
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		getLogger().info("Taking screenshot of failed case");
		failed(result.getMethod().getMethodName());
	}
	
}
