package tiki.admin;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_01_Manage_Product {
	@Test(groups = {"admin", "product"})
	public void Product_01_Create_Product() {
		System.out.println("Testcase01");
	}

	@Test(groups = {"admin", "product"})
	public void Product_02_View_Product() {
		System.out.println("Testcase02");
	}

	@Test(groups = {"admin", "product"})
	public void Product_03_Update_Product() {
		System.out.println("Testcase02");
	}

	@Test(groups = {"admin", "product"})
	public void Product_04_Delete_Product() {
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
