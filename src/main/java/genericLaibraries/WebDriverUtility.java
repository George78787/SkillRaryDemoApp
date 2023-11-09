package genericLaibraries;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all reusable methods to perform web driver actions
 * @author george
 *
 */
public class WebDriverUtility {
	WebDriver driver;
	/**
	 * This methods is used to user desired browser
	 * @param broser
	 * @return
	 */
	public WebDriver launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
			

		default:
		  System.out.println("invalid browser info");
		}
		driver.manage().window().maximize();
		return driver;
	}
	/**
	 * This method is used to navigate to the application
	 * @param url
	 */
	public void navigateToApp(String url) {
		driver.get(url);
	}
	/**
	 * This method is an implicitly wait statement
	 * @param time
	 */
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	/**
	 * This method is used to wait until element is visible
	 * @param time
	 * @param element
	 * @return
	 */
     public WebElement explicitWait(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method is used to wait until element is enabled
	 * @param element
	 * @param time
	 * @return
	 */
 
	public WebElement explicitWait(long time, WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method is used to until title of the web page appears
	 * @param title
	 * @param time
	 * @return
	 */
	public Boolean explicitwait(String title, long time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * This method is used to mouse hover on an element
	 * @element
	 */
	public void mouseHoverToElement(WebElement element) {
		Actions actions= new Actions(driver);
		actions.moveToElement(element).perform();
	}
	/**
	 * This method is used to double click on an element
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		Actions actions= new Actions(driver);
		actions.doubleClick(element).perform();
	}
	/**
	 * This method is used to right click on an element
	 */
	public void rightClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	/**
	 * This method is used to drag and drop and drop an element
	 * @param element
	 * @param target
	 */
	public void dragAndDropElement(WebElement element, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(element, target).perform();
	}
	/**
	 * This method is used to select an element from drop down based on index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This method is used to select an element from drop down based on value
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This method is used to select an element from drop down based on text
	 * @param element
	 * @param text
	 */
	public void handleDropDown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	/**
	 * This method is used to switch to frame based on index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to switch to frame based on id or name attribute
	 * @param idOrName
	 */
	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
	}
	/**
	 * This method is used to switch to frame based on frame element reference
	 * @param frameElement
	 */
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	/**
	 * This method is used to switch back from frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
		
	}
	public void takeScreenshot(WebDriver driver, String className, JavaUtility jutil) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+className + "_"+jutil.getCurrentTime()+".png");
		
		try {
			FileUtils.copyFile(src, dest);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to scroll till element
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	/**
	 * This method is used to handle alert
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert alert = driver.switchTo().alert();
		if(status.equalsIgnoreCase("ok"))
				alert.accept();
		else
			alert.dismiss();
	}
	/**
	 * This method is used to switch to child browser
	 */
	public void handleChildBrowser() {
		Set<String> set = driver.getWindowHandles();
		for(String windowID : set) {
			driver.switchTo().window(windowID);
		}
	}
	/**
	 * This method returns parent browser address
	 * @return
	 */
	public String getParentWindowID() {
		return driver.getWindowHandle();
	}
	/**
	 * This method is used to switch to specified window
	 * @param windowID
	 */
	public void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}
	/**
	 * This method id used to close current window
	 */
	public void closeCurrentWindow() {
		driver.close();
	}
	/**
	 * This method is used to close all the window
	 */
	public void closeAllWindows() {
		driver.quit();
	}
}
		



