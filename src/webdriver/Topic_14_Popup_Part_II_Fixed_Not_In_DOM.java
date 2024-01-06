package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Popup_Part_II_Fixed_Not_In_DOM {
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
		// ImplicitlyWait ảnh hưởng đến việc tìm Element (findElement, findElements)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		// Dùng By để định danh chưa đi tìm Element
		By loginPopup = By.cssSelector("div.styles__Root-sc-2hr4xa-0");
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		driver.findElement(By.cssSelector("div.Userstyle__MenuItemRevamp-sc-6e6am-19:nth-child(2)")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(),
				"Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(),
				"Mật khẩu không được để trống");
		driver.findElement(By.cssSelector("img.close-img")).click();
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	@Test
	public void TC_02_Fixed_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		By createByAccountPopup = By.cssSelector("div._8ien");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		Assert.assertEquals(driver.findElements(createByAccountPopup).size(), 1);
		driver.findElement(By.cssSelector("img._8idr")).click();
		Assert.assertEquals(driver.findElements(createByAccountPopup).size(), 0);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
