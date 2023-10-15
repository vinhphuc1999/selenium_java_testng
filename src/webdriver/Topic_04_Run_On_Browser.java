package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

//	@BeforeClass
//	public void beforeClass() {
//	}

	@Test
	public void TC_01_Run_Chrome() {
		
		// Chrome
		// Executable File - File thuc thi.
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.quit();
		
	}

	@Test
	public void TC_02_Run_Firefox() {
		// Firefox
		// Executable File - File thuc thi.
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.quit();
	}

	@Test
	public void TC_03_Run_Edge() {
		// Edge
		// Executable File - File thuc thi.
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.quit();
	}

//	@AfterClass
//	public void afterClass() {
//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.quit();
//	}
}
