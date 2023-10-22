package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// Tương tác với Element thì sẽ thông qua biến WebElement element
		// By: id/ class/ name/ xpath/ css/ tagname/ linkText/ partialLinkText

	}

	@Test
	public void TC_01_Verify_Error_Message() {
		driver.get("http://live.techpanda.org/");
		WebElement my_Account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
		my_Account.click();
		WebElement my_Account_1 = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
		my_Account_1.click();
		WebElement login = driver.findElement(By.xpath("//button[@id='send2']"));
		login.click();
		WebElement error_Message_Email = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
		String text_Error_Message_Email = error_Message_Email.getText();
		Assert.assertEquals(text_Error_Message_Email, "This is a required field.");
		WebElement error_Message_Pass_Word = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']"));
		String text_Error_Message_Pass_Word = error_Message_Pass_Word.getText();
		Assert.assertEquals(text_Error_Message_Pass_Word, "This is a required field.");
	}

	@Test
	public void TC_02_Verify_Error_Message_Email_Invalid() {
		driver.get("http://live.techpanda.org/");
		WebElement my_Account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
		my_Account.click();
		WebElement my_Account_1 = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
		my_Account_1.click();
		WebElement error_Message_Email = driver.findElement(By.xpath("//input[@id='email']"));
		error_Message_Email.sendKeys("pvphuc999@gmail.999");
		WebElement error_Message_Pass_Word = driver.findElement(By.xpath("//input[@id='pass']"));
		error_Message_Pass_Word.sendKeys("Pvpgtvt2017!");
		WebElement login = driver.findElement(By.xpath("//button[@id='send2']"));
		login.click();
		WebElement error_Message_Email_1 = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
		String text_Error_Message_Email_1 = error_Message_Email_1.getText();
		Assert.assertEquals(text_Error_Message_Email_1, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	@Test
	public void TC_03_Verify_Error_Message_Pass_Word() {
		driver.get("http://live.techpanda.org/");
		WebElement my_Account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
		my_Account.click();
		WebElement my_Account_1 = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
		my_Account_1.click();
		WebElement error_Message_Email = driver.findElement(By.xpath("//input[@id='email']"));
		error_Message_Email.sendKeys("pvphuc999@gmail.com");
		WebElement error_Message_Pass_Word = driver.findElement(By.xpath("//input[@id='pass']"));
		error_Message_Pass_Word.sendKeys("Pvp");
		WebElement login = driver.findElement(By.xpath("//button[@id='send2']"));
		login.click();
		WebElement error_Message_Pass_Word_1 = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"));
		String text_Error_Message_Pass_Word_1 = error_Message_Pass_Word_1.getText();
		Assert.assertEquals(text_Error_Message_Pass_Word_1, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Verify_Popup_Error() {
		driver.get("http://live.techpanda.org/");
		WebElement my_Account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
		my_Account.click();
		WebElement my_Account_1 = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
		my_Account_1.click();
		WebElement error_Message_Email = driver.findElement(By.xpath("//input[@id='email']"));
		error_Message_Email.sendKeys("pvphuc999@gmail.com");
		WebElement error_Message_Pass_Word = driver.findElement(By.xpath("//input[@id='pass']"));
		error_Message_Pass_Word.sendKeys("Pvpgtvt2017!");
		WebElement login = driver.findElement(By.xpath("//button[@id='send2']"));
		login.click();
		WebElement error_Popup = driver.findElement(By.xpath("//span[text()='Invalid login or password.']"));
		String text_Error_Popup = error_Popup.getText();
		Assert.assertEquals(text_Error_Popup, "Invalid login or password.");
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
