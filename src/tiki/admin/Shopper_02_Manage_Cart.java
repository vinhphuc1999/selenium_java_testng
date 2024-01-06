package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_02_Manage_Cart {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeTest (alwaysRun = true)
	public void initBrowser() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test(groups = { "admin", "cart" })
	public void Cart_01_Create_Visa() {
		System.out.println("Testcase01");
	}

	@Test(groups = { "admin", "cart" })
	public void Cart_02_View_Visa() {
		System.out.println("Testcase02");
	}

	@Test(groups = { "admin", "cart" })
	public void Cart_03_Update_Visa() {
		System.out.println("Testcase02");
	}

	@Test(groups = { "admin", "cart" })
	public void Cart_04_Delete_Visa() {
		System.out.println("Testcase02");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("BeforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("AfterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("BeforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass");
	}

	@AfterTest (alwaysRun = true)
	public void cleanBrowser() {
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("BeforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("AfterSuite");
	}

}
