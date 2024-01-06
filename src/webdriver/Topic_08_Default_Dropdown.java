package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	Action action;
	Select select_Day;
	Select select_Month;
	Select select_Year;
	Select select_Address;
	Select select_Job;
	Select select_Framework;
	Select select_Country;
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
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com/register");
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

	@Test
	public void TC_02_Check_Address() {
		driver.get("https://www.rode.com/wheretobuy");
		select_Address = new Select(driver.findElement(By.id("country")));
		Assert.assertFalse(select_Address.isMultiple());
		select_Address.selectByVisibleText("Vietnam");
		Assert.assertEquals(select_Address.getFirstSelectedOption().getText(), "Vietnam");
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		List<WebElement> title = driver.findElements(By.xpath("//h4[@class='text-left']"));
		System.out.println(title.size());
		for (WebElement webElement : title) {
			String name_Title = webElement.getText();
			System.out.println(name_Title);
		}
	}
	
	@Test
	public void TC_03_Register_New_Account_Continue() {
		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Phung");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Phung");
		select_Job = new Select(driver.findElement(By.id("Person_Role__c")));
		select_Job.selectByVisibleText("QA Manager / Director");
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Nam Long Software");
		select_Framework = new Select(driver.findElement(By.id("Test_Framework__c")));
		select_Framework.selectByVisibleText("Selenium");
		select_Country = new Select(driver.findElement(By.id("Self_Report_Country__c")));
		select_Country.selectByVisibleText("Vietnam");
		WebElement ele = driver.findElement(By.xpath("//input[@id='Opt_In_Compliance__c']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
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
