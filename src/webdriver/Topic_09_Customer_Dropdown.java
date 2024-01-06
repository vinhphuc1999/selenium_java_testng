package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Customer_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	Action action;
	Select select_Day;
	Select select_Month;
	Select select_Year;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String firstName, lastName, emailAddress, companyName, password, day, month, year;
	String number = String.valueOf(rand.nextInt(9999));

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Phung";
		lastName = "Phuc";
		emailAddress = "pvphuc" + getRandomNumber() + "@gmail.com";
		companyName = "Van Vinh Phuc";
		password = "Pvpgtvt2017!";
		day = "1";
		month = "May";
		year = "1980";

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		// Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hêt các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();
		// Chờ cho tất cả các item được load ra thành công
		// Locator phải lấy để đại diện cho tất cả các item
		// Lấy đến thẻ chưa text luôn
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@id='speed-button']")));

		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@value='M']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
		select_Day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select_Day.selectByVisibleText(day);
		Assert.assertEquals(select_Day.getOptions().size(), 32);
		select_Month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select_Month.selectByVisibleText(month);
		Assert.assertEquals(select_Month.getOptions().size(), 13);
		select_Year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select_Year.selectByVisibleText(year);
		Assert.assertEquals(select_Year.getOptions().size(), 112);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(companyName);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");
		driver.findElement(By.xpath("//a[@class='button-1 register-continue-button']")).click();
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		sleepInSecond(3);
		select_Day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select_Day.getFirstSelectedOption().getText(), day);
		select_Month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select_Month.getFirstSelectedOption().getText(), month);
		select_Year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select_Year.getFirstSelectedOption().getText(), year);
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
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
