package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_II {
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
	public void TC_01_Verify_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement title_Email = driver.findElement(By.xpath("//label[@for='mail']"));
		boolean display_Title_Email = title_Email.isDisplayed();
		WebElement text_Box_Email = driver.findElement(By.xpath("//input[@name='user_email']"));
		boolean display_Text_Box_Email = text_Box_Email.isDisplayed();
		WebElement title_Education = driver.findElement(By.xpath("//label[@for='edu']"));
		boolean display_Title_Education = title_Education.isDisplayed();
		WebElement text_Box_Education = driver.findElement(By.xpath("//textarea[@name='user_edu']"));
		boolean display_Text_Box_Education = text_Box_Education.isDisplayed();
		WebElement title_Age = driver.findElement(By.xpath("//label[text()='Age:']"));
		boolean display_Title_Age = title_Age.isDisplayed();
		WebElement radio_Button_Age = driver.findElement(By.xpath("//input[@id='under_18']"));
		boolean display_Radio_Button_Age = radio_Button_Age.isDisplayed();
		WebElement text_H5 = driver
				.findElement(By.xpath("//h5[text()='Name: User5']//parent::div[@class='figcaption']"));
		boolean display_Text_H5 = text_H5.isDisplayed();
		System.out.println(display_Title_Email);
		System.out.println(display_Text_Box_Email);
		System.out.println(display_Title_Education);
		System.out.println(display_Text_Box_Education);
		System.out.println(display_Title_Age);
		System.out.println(display_Radio_Button_Age);
		System.out.println(display_Text_H5);
		if ((display_Title_Email = true) && (display_Text_Box_Email = true) && (display_Title_Education = true)
				&& (display_Text_Box_Education = true) && (display_Title_Age) && (display_Radio_Button_Age = true)) {
			text_Box_Email.sendKeys("Automation Testing");
			text_Box_Education.sendKeys("Automation Testing");
			WebElement under_18 = driver.findElement(By.xpath("//input[@id='under_18']"));
			under_18.click();
		} else {
			System.out.println("No display in screen !!!");
		}
		if (display_Title_Email == true) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
		if (display_Text_H5 == true) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
	}

	@Test
	public void TC_02_Verify_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement text_Box_Email = driver.findElement(By.xpath("//input[@name='user_email']"));
		boolean enabled_Text_Box_Email = text_Box_Email.isEnabled();
		WebElement radio_Button_Age = driver.findElement(By.xpath("//input[@id='under_18']"));
		boolean enabled_Radio_Button_Age = radio_Button_Age.isEnabled();
		WebElement text_Box_Education = driver.findElement(By.xpath("//textarea[@name='user_edu']"));
		boolean enabled_Text_Box_Education = text_Box_Education.isEnabled();
		WebElement drop_Down_Job_1 = driver.findElement(By.xpath("//select[@id='job1']"));
		boolean enabled_Drop_Down_Job_1 = drop_Down_Job_1.isEnabled();
		WebElement drop_Down_Job_2 = driver.findElement(By.xpath("//select[@id='job2']"));
		boolean enabled_Drop_Down_Job_2 = drop_Down_Job_2.isEnabled();
		WebElement check_Box_Interests = driver.findElement(By.xpath("//input[@id='development']"));
		boolean enabled_check_Box_Interests = check_Box_Interests.isEnabled();
		WebElement scroll_Slider = driver.findElement(By.xpath("//input[@id='slider-1']"));
		boolean enabled_scroll_Slider = scroll_Slider.isEnabled();

		WebElement text_Box_Password = driver.findElement(By.xpath("//input[@id='disable_password']"));
		boolean enabled_text_Box_Password = text_Box_Password.isEnabled();
		WebElement radio_Button_Disable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		boolean enabled_radio_Button_Disable = radio_Button_Disable.isEnabled();
		WebElement text_Box_Biography = driver.findElement(By.xpath("//textarea[@name='user_bio']"));
		boolean enabled_text_Box_Biography = text_Box_Biography.isEnabled();
		WebElement drop_Down_Job_3 = driver.findElement(By.xpath("//select[@id='job3']"));
		boolean enabled_Drop_Down_Job_3 = drop_Down_Job_3.isEnabled();
		WebElement check_Box_Interests_Disabled = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		boolean enabled_check_Box_Interests_Disabled = check_Box_Interests_Disabled.isEnabled();
		WebElement scroll_Slider_1 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		boolean enabled_scroll_Slider_1 = scroll_Slider_1.isEnabled();

		if ((enabled_Text_Box_Email == true) && (enabled_Radio_Button_Age == true)
				&& (enabled_Text_Box_Education == true) && (enabled_Drop_Down_Job_1 == true)
				&& (enabled_Drop_Down_Job_2 == true) && (enabled_check_Box_Interests == true)
				&& (enabled_scroll_Slider == true)) {
			System.out.println("Element is enable !!!");
		} else {
			System.out.println("Element is disable !!!");
		}

		if ((enabled_text_Box_Password == true) && (enabled_radio_Button_Disable == true)
				&& (enabled_text_Box_Biography == true) && (enabled_Drop_Down_Job_3 == true)
				&& (enabled_check_Box_Interests_Disabled == true) && (enabled_scroll_Slider_1 == true)) {
			System.out.println("Element is enable !!!");
		} else {
			System.out.println("Element is disable !!!");
		}
	}

	@Test
	public void TC_03_Verify_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement radio_Button_Age = driver.findElement(By.xpath("//input[@id='under_18']"));
		radio_Button_Age.click();
		boolean select_Radio_Button_Age = radio_Button_Age.isSelected();
		WebElement check_Box_Language = driver.findElement(By.xpath("//input[@id='java']"));
		check_Box_Language.click();
		boolean select_Check_Box_Language = check_Box_Language.isSelected();
		System.out.println(select_Radio_Button_Age);
		System.out.println(select_Check_Box_Language);
		check_Box_Language.click();
		boolean select_Check_Box_Language_1 = check_Box_Language.isSelected();
		System.out.println(select_Check_Box_Language_1);
	}

	@Test
	public void TC_04_Register() {
		driver.get("https://login.mailchimp.com/signup/");
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		email.clear();
		email.sendKeys("pvphuc999@gmail.com");
		String value_Email = email.getAttribute("value");
		email.sendKeys(Keys.TAB);
		WebElement user_Name = driver.findElement(By.xpath("//input[@id='new_username']"));
		String value_User_Name = user_Name.getAttribute("value");
		if (value_User_Name == value_Email) {
			System.out.println("Pass !!!");
		} else {
			System.out.println("Fail !!!");
		}
		user_Name.sendKeys(Keys.TAB);
		WebElement pass_Word = driver.findElement(By.xpath("//input[@id='new_password']"));
		pass_Word.sendKeys("Pvpgtvt2017!");
		WebElement condition_1 = driver.findElement(By.xpath("//div[@class='group size1of1']"));
		Boolean variable_Condition_1 = condition_1.isDisplayed();
		System.out.println(variable_Condition_1);
		if ((variable_Condition_1 == true) && (variable_Condition_1 == true) && (variable_Condition_1 == true)
				&& (variable_Condition_1 == true) && (variable_Condition_1 == true) && (variable_Condition_1 == true)) {
			System.out.println("Check again password !!!");
		} else {
			WebElement check_Box_Rules = driver.findElement(By.xpath("//input[@name='marketing_newsletter']"));
			check_Box_Rules.click();
		}
		WebElement sign_Up = driver.findElement(By.xpath("//button[@id='create-account-enabled']"));
		sign_Up.click();

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
