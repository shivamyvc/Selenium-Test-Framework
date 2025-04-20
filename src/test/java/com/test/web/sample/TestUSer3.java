package com.test.web.sample;

import base.test.BaseTest;
import extent.utils.ExtentTestManager;

import com.utilities.ExcelReportUtils;
import com.utilities.VerificationUtils;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import java.util.HashMap;

@Listeners(com.listeners.ExtentTestListener.class)
public class TestUSer3 extends BaseTest {
	@Test(dataProvider = "excelData")
	public void runTest(HashMap<String, String> data) {
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

		// Setting Some VP's here
		VP.verifyEquals(actual, expected, "Verifying Usename");

	}

}
