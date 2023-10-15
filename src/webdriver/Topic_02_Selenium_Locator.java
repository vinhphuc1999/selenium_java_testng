package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Open website Register
		driver.get("https://demo.nopcommerce.com/register");


	}

	// Code HTML of FirstName textbox 
	// <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
	// Tag name of element (TagName HTML): input
	// Attribute name: type, data-val, data-val-required, id, name
	// Attribute value: text, true, First name is required, FirstName, FirstName
	
	@Test
	public void TC_01_ID() {
	// Thao tac len Element nao thi phai tim Element do: findElement
	// Tim kiem theo: id/ class/ name/ css/ xpath...
	// Tim kem thay Element roi thi action len Element do: clic/ sendkey...
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		}

	@Test
	public void TC_02_Class() {
		// Mo man hinh search
		driver.get("https://demo.nopcommerce.com/search");
		
		// Nhap text vao search Text-box
		driver.findElement(By.className("search-text")).sendKeys("MacBook");
		
	}

	@Test
	public void TC_03_Name() {
		// Click vao Advanced Search Check-box
		driver.findElement(By.name("advs")).click();
		
	}
	
	@Test
	public void TC_04_TagName() {
		// Tim kiem bao nhieu the input tren man hinh hien tai
		System.out.println(driver.findElements(By.tagName("input")).size());		
	}
	
	@Test
	public void TC_05_LinkText() {
		// Click vao duong link Addresses
		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_PartialLinkText() {
		// Click vao duong link nhung khong chinh xac nhat (chi chua vai tu trong do)
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css() {
		// Mo lai trang Register
		driver.get("https://demo.nopcommerce.com/register");
		
		// 1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		
		// 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		
		// 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("Automation@gmail.com");
	}
	
	@Test
	public void TC_08_Xpath() {
		// Mo lai trang Register
				driver.get("https://demo.nopcommerce.com/register");
				
				// 1
				driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
				
				// 2
				driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
				
				// 3
				driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("Automation@gmail.com");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
