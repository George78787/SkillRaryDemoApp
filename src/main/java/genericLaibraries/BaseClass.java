package genericLaibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pomPages.AddNewCategoryPage;
import pomPages.AddNewCourse;
import pomPages.AddNewUserPage;
import pomPages.AdminHomePage;
import pomPages.CategoryPage;
import pomPages.CourseListPage;
import pomPages.LoginPage;
import pomPages.UsersPage;
import pomPages.WelcomePage;

public class BaseClass {

	//@BeforeSuite
	//@BeforeTest
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected JavaUtility jutil;
	protected WebDriverUtility webUtil;
	protected WebDriver driver;
	
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	
	protected WelcomePage welcome;
	protected LoginPage login;
	protected AdminHomePage home;
	protected UsersPage users;
	protected CourseListPage course;
	protected CategoryPage category;
	protected AddNewCategoryPage addCategory;
	protected AddNewCourse addCourse;
	protected AddNewUserPage addUser;
	
	@BeforeClass
	public void classConfig(){
		property = new PropertiesUtility();
		excel= new ExcelUtility();
		jutil = new JavaUtility();
		webUtil = new WebDriverUtility();
		
		property.propertiesInitialization(IConstantPath.PROEPERTIES_PATH);
		driver = webUtil.launchBrowser(property.readfromProperties("browser"));
		
		sdriver = driver;
		sjutil = jutil;
		
	}
	@BeforeMethod
	public void methodConfig() {
		excel.excelInitialization(IConstantPath.EXCEL_PATH);
		
		welcome = new WelcomePage(driver);
		login = new LoginPage(driver);
		home = new AdminHomePage(driver);
		users = new UsersPage(driver);
		course = new CourseListPage(driver);
		category = new CategoryPage(driver);
		addUser = new AddNewUserPage(driver);
		addCourse = new AddNewCourse(driver);
		addCategory = new AddNewCategoryPage(driver);
		
		webUtil.navigateToApp(property.readfromProperties("url"));
		Assert.assertEquals(welcome.getLogo(),"SkillRary-Ecommerce");
		
		long time =Long.parseLong(property.readfromProperties("timeouts"));
		webUtil.waitTillElementFound(time);
		
		welcome.clickLoginButton();
		Assert.assertEquals(login.getPageHeader(), "Login");
		
		login.setEmail(property.readfromProperties("username"));
		login.setPassword(property.readfromProperties("password"));
		login.clickLogin();
		
		Assert.assertEquals(home.getAdminIcon(), "SkillRary Admin");
		
	}
	@AfterMethod
	public void methodTeardown() {
		excel.closeExcel();
		home.clickSignOut();
	}
	@AfterClass
	public void classTeardown() {
		webUtil.closeAllWindows();
	}
	
}
