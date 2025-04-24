package com.test.web.sample;

import base.test.BaseTest;
import extent.utils.ExtentTestManager;

import com.driver.factory.DriverFactory;
import com.utilities.ExcelReportUtils;
import com.utilities.VerificationUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import java.util.HashMap;

@Listeners(com.listeners.ExtentTestListener.class)
public class TestUSer3 extends BaseTest {
	@Test(dataProvider = "excelData")
	public void runTest(HashMap<String, String> data) throws InterruptedException {
		// Setting up report for each test case
		ExcelReportUtils.getReport().clear();
		ExcelReportUtils.getReport().put("TestCaseID", data.get("TestCaseID"));
		ExcelReportUtils.getReport().put("TestDataID", data.get("TestDataID"));
		ExcelReportUtils.getReport().put("Expected", data.get("Expected"));
		String expected = data.get("Expected");
		String actual = data.get("Username");
		String errorMessage = "";
		ExtentTestManager.getTest().getModel().setName(data.get("TestCaseID"));
		VerificationUtils VP = new VerificationUtils(ExtentTestManager.getTest());

		// Start Your Script Here
		System.out.println("Running test for: " + data.get("TestCaseID"));
		ExcelReportUtils.getReport().put("Actual", actual);

		// Initialize Driver Here
		DriverFactory.setDriver(data.get("Browser"));
		WebDriver driver = DriverFactory.getDriver();
		driver.get(data.get("URL"));
//		Thread.sleep(5000);
		String h1Tag=driver.findElement(By.xpath("//h1")).getText();
		// Setting Some VP's here
		VP.verifyEquals(actual, expected, "Verifying Usename");
		System.out.println("The Title Head is "+h1Tag);

	}
	
	
	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
