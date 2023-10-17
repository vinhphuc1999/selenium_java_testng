package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
		// Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// Tương tác với Element thì sẽ thông qua biến WebElement element
		// By: id/ class/ name/ xpath/ css/ tagname/ linkText/ partialLinkText

	}

	@Test
	public void TC_01_() {
		// Tương tác với Element thì phải tìm được Element đó
		// Thông qua locator của nó
		driver.get("https://demo.nopcommerce.com/register");
		WebElement element = driver.findElement(By.xpath(""));
		// Xóa dữ liệu đi trước khi nhập text
		// Dùng cho các textbox/ textarea/ dropdown (Edit table)
		element.clear();
		// Nhập dữ liệu
		element.sendKeys("");
		// Click vào các button/ link/ checkbox/ radio/ v.v
		element.click();
		// Lấy giá trị của các thuộc tính
		String searchAttribute = element.getAttribute("");
		// GUI: Font/ Size/ Location / .v.v
		// Lấy giá trị CSS
		element.getCssValue(searchAttribute);
		// Giá vị trí của element so với website
		Point point = element.getLocation();
		point.x = 324;
		point.y = 324;
		// Kích thước của element
		Dimension di = element.getSize();
		di.getHeight();
		di.getWidth();
		// Kết hợp cả Location và getSize
		Rectangle rec = element.getRect();
		// Chụp hình khi testcase fail
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BASE64);
		// Cần lấy tên thẻ HTML của element đó
		element.getTagName();
		// Lấy text từ Error message success
		element.getText();
		// Dùng để verify xem 1 element hiển thị hoặc không
		// Sử dụng cho tất cả Element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		// Dùng để verify xem 1 element có thao tác được hay không
		// Sử dụng cho tất cả Element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		// Dùng để verify xem 1 element có được chọn hay chưa
		// Sử dụng cho Checkbox/ Radio
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		// Các element nằm trong thẻ form
		// Tương ứng với hành vi enter của end-user
		element.submit();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
