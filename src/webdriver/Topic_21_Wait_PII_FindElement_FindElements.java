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

public class Topic_21_Wait_PII_FindElement_FindElements {
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
		driver.get("https://www.techpanda.org/");
		// Đang apply 10s cho việc tìm element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_FindElement() {
		// Thao tác 1 lần
		// Tìm thấy duy nhất 1 element/node
		// Tìm thấy và thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên không cần phải chờ hết timeout 10s
		driver.findElement(By.cssSelector("input[name='email']"));
		// Tìm thấy nhiều hơn 1 element/node
		// Sẽ thao tác lên element/node đầu tiên
		// Không quan tâm các element/node còn lại
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("pvphuc999@gmail.com");
		// Không tìm thấy element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà tìm thấy element thì thỏa mãn điều kiện
		// => Pass
		// Nếu trong 10s mà không tìm thấy element thì
		// +Đánh fail testcase tại step này 
		// +Không chạy step tiếp theo
		// +Throw ra 1 ngoại lệ: NoSuchElementException
		driver.findElement(By.cssSelector("input[name='check']"));
	}

	@Test
	public void TC_02_FindElements() {
		// Thao tác 1 lần
		// Tìm thấy duy nhất 1 element/node
		// Tìm thấy và lưu nó vào list = 1 element
		// Tìm thấy và thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên không cần phải chờ hết timeout 10s
		List<WebElement> elements = driver.findElements(By.cssSelector("input[name='email']"));
		System.out.println("List element number: " + elements.size());
		// Tìm thấy nhiều hơn 1 element/node
		// Sẽ thao tác lên element/node đầu tiên
		// Không quan tâm các element/node còn lại
		elements = driver.findElements(By.cssSelector("input[name='email']"));
		System.out.println("List element number: " + elements.size());
		// Không tìm thấy element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà tìm thấy element thì thỏa mãn điều kiện
		// => Pass
		// Nếu trong 10s mà không tìm thấy element thì
		// +Không đánh fail testcase
		// +Vẫn chạy step tiếp theo
		// +Trả về 1 list rỗng(emty) = 0
		elements = driver.findElements(By.cssSelector("input[name='check']"));
		System.out.println("List element number: " + elements.size());
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
