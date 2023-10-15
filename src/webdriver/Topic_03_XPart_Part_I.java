package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPart_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedrive");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Emty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Phung Vinh Phuc");
		driver.findElement(By.id("txtEmail")).sendKeys("123@");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@");
		driver.findElement(By.id("txtPassword")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.id("txtCPassword")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.id("txtPhone")).sendKeys("0986745978");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Phung Vinh Phuc");
		driver.findElement(By.id("txtEmail")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@");
		driver.findElement(By.id("txtPassword")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.id("txtCPassword")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.id("txtPhone")).sendKeys("0986745978");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Phung Vinh Phuc");
		driver.findElement(By.id("txtEmail")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0986745978");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");	
	}
	
	@Test
	public void TC_05_Invalid_Confirm_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Phung Vinh Phuc");
		driver.findElement(By.id("txtEmail")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.id("txtCPassword")).sendKeys("Pvpgtvt2017");
		driver.findElement(By.id("txtPhone")).sendKeys("0986745978");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");	
	}
	
	@Test
	public void TC_06_Invalid_Phone() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Phung Vinh Phuc");
		driver.findElement(By.id("txtEmail")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("pvphuc999@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Pvpgtvt2017!");
		driver.findElement(By.id("txtCPassword")).sendKeys("Pvpgtvt2017");
		driver.findElement(By.id("txtPhone")).sendKeys("098674597");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
