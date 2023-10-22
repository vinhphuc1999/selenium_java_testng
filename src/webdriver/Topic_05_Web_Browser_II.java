package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}

		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// Tương tác với Element thì sẽ thông qua biến WebElement element

	}

	@Test
	public void TC_01_Verify_Url() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		String url_Account = driver.getCurrentUrl();
		Assert.assertEquals(url_Account, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String url_Create = driver.getCurrentUrl();
		Assert.assertEquals(url_Create, "http://live.techpanda.org/index.php/customer/account/create/");
		sleepInSecond(3000);
	}

	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		String title_Login = driver.getTitle();
		Assert.assertEquals(title_Login, "Customer Login");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String title_Create = driver.getTitle();
		Assert.assertEquals(title_Create, "Create New Customer Account");
		sleepInSecond(3000);
	}

	@Test
	public void TC_03_Navigate_Funtion() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String url_Create = driver.getCurrentUrl();
		Assert.assertEquals(url_Create, "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		String url_Account = driver.getCurrentUrl();
		Assert.assertEquals(url_Account, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		String title_Create = driver.getTitle();
		Assert.assertEquals(title_Create, "Create New Customer Account");
		sleepInSecond(3000);
	}

	@Test
	public void TC_04_Verify_Page_Source() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		String page_Source_Account = driver.getPageSource();
		Assert.assertTrue(page_Source_Account.contains("Login or Create an Account"));
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String page_Source_Create = driver.getPageSource();
		Assert.assertTrue(page_Source_Create.contains("Create an Account"));
		sleepInSecond(3000);
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
