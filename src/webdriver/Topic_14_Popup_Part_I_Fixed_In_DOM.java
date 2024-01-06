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

public class Topic_14_Popup_Part_I_Fixed_In_DOM {
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
	}

	@Test
	public void TC_01_Fixed_In_DOM_1() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		// Verify popup is undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.login_")).click();
		// Verify popup is displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("div[style='display: block; padding-right: 17px;'] input#account-input"))
				.sendKeys("automationfc");
		driver.findElement(By.cssSelector("div[style='display: block; padding-right: 17px;'] input#password-input"))
				.sendKeys("automationfc");
		driver.findElement(
				By.cssSelector("div[style='display: block; padding-right: 17px;'] button[onclick='onLoginUser(this)']"))
				.click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");
	}

	@Test
	public void TC_02_Fixed_In_DOM_2() {
		driver.get("https://skills.kynaenglish.vn/");
		WebElement ele = driver
				.findElement(By.xpath("//div[@class='mobile-button-wrap'] //a[@class='mobile-button-header']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		sleepInSecond(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb'] //div[@class='modal-dialog']"))
						.isDisplayed());
		driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb'] //input[@id='user-login']"))
				.sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb'] //input[@id='user-password']"))
				.sendKeys("123456");
		driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb'] //button[@id='btn-submit-login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Sai tên đăng nhập hoặc mật khẩu']")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");
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
