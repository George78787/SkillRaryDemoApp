package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {

	//Declaration
	@FindBy(xpath = "//span[text()='SkillRary Admin']")
	private WebElement adminIcon;
	
	@FindBy(xpath ="//span[text()='Users']")
	private WebElement usersTab;
	
	@FindBy(xpath ="//span[text()='Courses']")
	private WebElement CoursesTab;
	
	@FindBy(xpath ="//a[.=' Course List']")
	private WebElement CourseLink;
	
	@FindBy(xpath = "//a[.=' Category']")
	private WebElement catagoryLink;
	
	@FindBy(xpath = "//a[text()='Sign out']")
	private WebElement signOutLink;
	
	
	//initialization
	public AdminHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public String getAdminIcon() {
		return adminIcon.getText();
	}
	public void clickUsersTab(){
		usersTab.click();
		
	}
	public void clickCoursesTab() {
		CoursesTab.click();
	}
	public void clickCategoryLink() {
		catagoryLink.click();
	}
	public void clickSignOut() {
		signOutLink.click();
	}
	
}
