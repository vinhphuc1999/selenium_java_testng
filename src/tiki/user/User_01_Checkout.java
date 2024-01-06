package tiki.user;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class User_01_Checkout {
	@Test(groups = "user")
	public void Checkout_01_Add_Product_To_Cart() {
		System.out.println("Testcase01");
	}

	@Test(groups = "user")
	public void Checkout_02_View_Cart() {
		System.out.println("Testcase02");
	}

	@Test(groups = "user")
	public void Checkout_03_Add_Shipping_Address() {
		System.out.println("Testcase02");
	}

	@Test(groups = "user")
	public void Checkout_04_Add_Payment_Infor() {
		System.out.println("Testcase02");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("BeforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("AfterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("BeforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("BeforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("BeforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("AfterSuite");
	}

}
