package genericLaibraries;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ListenersImplimentation implements ITestListener {
	public void onTestStart1(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+ "execution start");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getMethod().getMethodName()+ "pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	WebDriverManager.chromedriver().setup();
	System.out.println("Failure occured due to:"+result.getThrowable());
	
	WebDriverUtility web = new WebDriverUtility();
	web.takeScreenshot(BaseClass.sdriver, result.getMethod().getMethodName(), BaseClass.sjutil);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getMethod().getMethodName()+"skipped");
		System.out.println("skipped due to:"+ result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("suite execution starts");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("suite execution ends");
	}

	public void onTestStart(ITestResult result) {
		
	}
}
