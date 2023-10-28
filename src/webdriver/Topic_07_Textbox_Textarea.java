package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random ran = new Random();
	Action action;

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

//	@Test
//	public void TC_01_Sign_Up_Account() {
//		driver.get("http://live.techpanda.org/");
//		WebElement my_Account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
//		my_Account.click();
//		WebElement my_Account_1 = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
//		my_Account_1.click();
//		WebElement create_Button = driver.findElement(By.xpath("//a[@title='Create an Account']"));
//		create_Button.click();
//		sleepInSecond(3);
//		WebElement text_Box_First_Name = driver.findElement(By.xpath("//input[@id='firstname']"));
//		text_Box_First_Name.sendKeys("Phung");
//		String value_text_Box_First_Name = text_Box_First_Name.getAttribute("value");
//		WebElement text_Box_Middle_Name = driver.findElement(By.xpath("//input[@id='middlename']"));
//		text_Box_Middle_Name.sendKeys("Vinh");
//		String value_text_Box_Middle_Name = text_Box_Middle_Name.getAttribute("value");
//		WebElement text_Box_Last_Name = driver.findElement(By.xpath("//input[@id='lastname']"));
//		text_Box_Last_Name.sendKeys("Phuc");
//		String value_text_Box_Last_Name = text_Box_Last_Name.getAttribute("value");
//		String total = (value_text_Box_First_Name) + " " + (value_text_Box_Middle_Name) + " "
//				+ (value_text_Box_Last_Name);
//		System.out.println(total);
//		WebElement text_Box_Email_Address = driver.findElement(By.xpath("//input[@id='email_address']"));
//		text_Box_Email_Address.sendKeys("pvphuc" + (ran.nextInt(9999)) + "@gmail.com");
//		String value_text_Box_Email_Address = text_Box_Email_Address.getAttribute("value");
//		System.out.println(value_text_Box_Email_Address);
//		WebElement text_Box_Pass_Word = driver.findElement(By.xpath("//input[@id='password']"));
//		text_Box_Pass_Word.sendKeys("Pvpgtvt2017!");
//		WebElement text_Box_Confirm_Pass_Word = driver.findElement(By.xpath("//input[@id='confirmation']"));
//		text_Box_Confirm_Pass_Word.sendKeys("Pvpgtvt2017!");
//		WebElement check_Box_Rule = driver.findElement(By.xpath("//input[@id='is_subscribed']"));
//		check_Box_Rule.click();
//		WebElement button_Register = driver.findElement(By.xpath("//span[text()='Register']"));
//		button_Register.click();
//		sleepInSecond(3);
//		WebElement message_Register_Success = driver
//				.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']"));
//		String text_Message_Register_Success = message_Register_Success.getText();
//		Assert.assertEquals(text_Message_Register_Success, "Thank you for registering with Main Website Store.");
//		WebElement contact_Information = driver.findElement(By.xpath("//div[@class='col-1']//p"));
//		contact_Information.isDisplayed();
//		String text_Contact_Information = contact_Information.getText();
//		Assert.assertTrue(text_Contact_Information.contains(total));
//		Assert.assertTrue(text_Contact_Information.contains(value_text_Box_Email_Address));
//		sleepInSecond(3);
//		WebElement my_Account_2 = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
//		my_Account_2.click();
//		WebElement log_Out = driver.findElement(By.xpath("//a[@title='Log Out']"));
//		log_Out.click();
//		sleepInSecond(7);
//		String title = driver.getTitle();
//		Assert.assertEquals(title, "Home page");
//	}

	@Test
	public void TC_02_Verify_Information() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//input[@name='username']")).clear();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();
		
		driver.findElement(By.xpath("//span[text()='PIM']/ancestor::li[@class='oxd-main-menu-item-wrapper']")).click();
		
		driver.findElement(By.xpath("//a[text()='Add Employee']/parent::li[@class]")).click();
		
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Phung");
		driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys("Vinh");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Phuc");
		driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")).click();
		driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--focus']")).sendKeys(Keys.CONTROL+"A");
		driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--focus']")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--focus']")).sendKeys("09101913");
		driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
		driver.findElement(By.xpath("//div[@class='oxd-form-row']/descendant::input[1][@class='oxd-input oxd-input--active']")).sendKeys("pvphuc09109913");
		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active' and @type='password'])[1]")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
		
		String first_Name = driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
		Assert.assertEquals(first_Name, "Phung");
		String last_Name = driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
		Assert.assertEquals(last_Name, "Phuc");
		String id = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div[@class='oxd-input-group__label-wrapper']/following-sibling::div/child::input[@class='oxd-input oxd-input--active']")).getAttribute("value");
		Assert.assertEquals(id, "09101913");
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/child::input[@class='oxd-input oxd-input--active']")).sendKeys("0910-1999-0910");
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys("devtest.");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).click();
		String note = driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value");
		Assert.assertEquals(note, "devtest.");
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/child::input[@class='oxd-input oxd-input--active']")).click();
		String number_1 = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/child::input[@class='oxd-input oxd-input--focus']")).getAttribute("value");
		Assert.assertEquals(number_1, "0910-1999-0910");
		driver.findElement(By.xpath("//li[@class='oxd-userdropdown']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}
	

//	@Test
//	public void TC_03_Verify_Error_Message_Pass_Word() {
//		driver.get("http://live.techpanda.org/");
//		WebElement my_Account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
//		my_Account.click();
//		WebElement my_Account_1 = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
//		my_Account_1.click();
//		WebElement error_Message_Email = driver.findElement(By.xpath("//input[@id='email']"));
//		error_Message_Email.sendKeys("pvphuc999@gmail.com");
//		WebElement error_Message_Pass_Word = driver.findElement(By.xpath("//input[@id='pass']"));
//		error_Message_Pass_Word.sendKeys("Pvp");
//		WebElement login = driver.findElement(By.xpath("//button[@id='send2']"));
//		login.click();
//		WebElement error_Message_Pass_Word_1 = driver
//				.findElement(By.xpath("//div[@id='advice-validate-password-pass']"));
//		String text_Error_Message_Pass_Word_1 = error_Message_Pass_Word_1.getText();
//		Assert.assertEquals(text_Error_Message_Pass_Word_1,
//				"Please enter 6 or more characters without leading or trailing spaces.");
//	}
//
//	@Test
//	public void TC_04_Verify_Popup_Error() {
//		driver.get("http://live.techpanda.org/");
//		WebElement my_Account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
//		my_Account.click();
//		WebElement my_Account_1 = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
//		my_Account_1.click();
//		WebElement error_Message_Email = driver.findElement(By.xpath("//input[@id='email']"));
//		error_Message_Email.sendKeys("pvphuc999@gmail.com");
//		WebElement error_Message_Pass_Word = driver.findElement(By.xpath("//input[@id='pass']"));
//		error_Message_Pass_Word.sendKeys("Pvpgtvt2017!");
//		WebElement login = driver.findElement(By.xpath("//button[@id='send2']"));
//		login.click();
//		WebElement error_Popup = driver.findElement(By.xpath("//span[text()='Invalid login or password.']"));
//		String text_Error_Popup = error_Popup.getText();
//		Assert.assertEquals(text_Error_Popup, "Invalid login or password.");
//	}

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
