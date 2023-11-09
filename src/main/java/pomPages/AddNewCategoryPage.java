package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCategoryPage {

	//Declaration
	@FindBy(xpath = "//b[text()='Add New Category']")
	private WebElement pageHeder;
	
	@FindBy(id ="name" )
	private WebElement nameTF;
	
	@FindBy(xpath = "//button[text()=' Save']")
	private WebElement saveButton;
	
  //initialization
	public AddNewCategoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public String getPageHeader() {
		return pageHeder.getText();
	}
	public void setName(String categoryName) {
		nameTF.sendKeys(categoryName);
	}
	public void clickSave() {
		saveButton.click();
	}
	
}
