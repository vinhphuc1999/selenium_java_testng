package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import listener.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class Topic_09_Listener {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@Parameters("browser")
	@BeforeClass
	// If-else
	public void beforeClass(@Optional("firefox") String browserName) {
		// Switch-case
		switch (browserName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Please input with correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Parameters("enviroment")
	@Test()
	public void TC_01_LoginToSystem(@Optional("live") String envName) {
		String envUrl = getEnviromentUrl(envName);
		driver.get(envUrl + "index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys("admin@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("admin");
		driver.findElement(loginButton).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("admin@gmail.com"));

		// ....

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	public String getEnviromentUrl(String envName) {
		if (envName.equals("dev")) {
			return "http://live.techpanda.org/";
		} else if (envName.equals("test")) {
			return "http://test.techpanda.org/";
		} else if (envName.equals("live")) {
			return "http://live.techpanda.org/";
		} else {
			return null;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
