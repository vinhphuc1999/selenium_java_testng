package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_07_Loop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 5)
	public void Register() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");

		driver.findElement(By.id("firstname")).sendKeys("");
		driver.findElement(By.id("lastname")).sendKeys("");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(""));

		// ....

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
