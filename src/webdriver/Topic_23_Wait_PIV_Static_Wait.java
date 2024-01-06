package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Wait_PIV_Static_Wait {
	WebDriver driver;
	Random ran = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email = "pvphuc" + getRandomNumber() + "@gmail.com";
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	String CCCD = "z4226170206224_8dbfadb6b980a81ffde6a0392546ac1a.jpg";
	String CCCD_1 = "z4226170253468_394943efba8bdce07798d6d1fdc821ad.jpg";

	String CCCDFilePath = projectPath + "\\uploadFiles" + CCCD;
	String CCCDFilePath_1 = projectPath + "\\uploadFiles" + CCCD;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		// ImplicitlyWait ảnh hưởng đến việc tìm Element (findElement, findElements)
		driver.manage().window().maximize();
		// Ép kiểu của driver sang JavascriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		// Đang apply 10s cho việc tìm element
		// 1 - Ảnh hưởng trực tiếp tới 2 hàm findElement/findElements
		// 2 - Ngoại lệ
		// +ImplicitWait set ở đâu thì sẽ apply từ đó trở xuống
		// +Nếu bị gán lại ở đâu thì gán giá trị mới, không gán giá trị cũ nữa
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// +Nếu không set thì ImplicitWait mặc định bằng 0

	}

	@Test
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	@Test
	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	// Hàm thực thi code javascript
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	// Hàm lấy domain
	public String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain;");
	}

	// Hàm lấy text
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	// Hàm scroll đến cuối trang
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	// Hàm di chuyển trang url
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	// Hàm click vào một element
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	// Hàm scroll đến đầu trang
	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	// Hàm scroll đến cuối trang
	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');",
				getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	// Hàm senkey
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');",
				getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	// Dùng được cho duy nhất 2 ID (Window / Tab)
	public void switchToWindowByID(String oderID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set không cho phép trùng/ null
		// Dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(oderID)) {
				driver.switchTo().window(id);
			}
		}
	}

	// Dùng được cho từ 2 ID (Window / Tab) trờ lên
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set không cho phép trùng/ null
		int count = 1;
		// Dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			// Switch từng ID trước
			driver.switchTo().window(id);
			// Lấy ra title của page này
			String actualPageTitle = driver.getTitle();
			System.out.println("Actual title = " + actualPageTitle + " - " + count);
			count++;
			// Kiểm tra title page hiện tại với title page mong muốn
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	// Đóng tất cả các Window ngoại trừ main Window
	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set không cho phép trùng/ null
		// Dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
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
