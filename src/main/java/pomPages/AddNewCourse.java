package pomPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLaibraries.WebDriverUtility;

public class AddNewCourse {

	//Declaration
	@FindBy(xpath = "//b[text()='Add New Category']")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//input[@id=\"name\"]")
	private WebElement nameTF;
	
	@FindBy(id ="category")
	private WebElement categoryDropDown;
	
	@FindBy(id = "price")
	private WebElement priceTF;
	
	@FindBy(id="photo")
	private WebElement photoTF;
	
	@FindBy(xpath = "//button[@name='add']")
	private WebElement saveButton;
	
	
	@FindBy(xpath = "//html/body/p")
	private WebElement descriptionTextArea;
	
	@FindBy(xpath = "//iframe[@title='Rich Text Editor, editor1']")
	private WebElement description;
	//initialization
	public AddNewCourse(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public String getPageHeader() {
		return pageHeader.getText();
	}
	public void setName(String courseName) {
		nameTF.sendKeys(courseName);
	}
	public void selectCategory(WebDriverUtility web,String category) {
		web.handleDropDown(category, categoryDropDown);
	}
	public void setPrice(String price) {
		priceTF.sendKeys(price);
	}
	public void uploadPhoto(String photoPath) {
		photoTF.sendKeys(photoPath);
	}
	public void setDescription(WebDriverUtility web, String text ) {
		web.switchToFrame(description);
		descriptionTextArea.sendKeys(text);
		web.switchBackFromFrame();
	}
	public void clickSaveButton() {
		saveButton.click();
	}
	
	
	
	
	
	
}
