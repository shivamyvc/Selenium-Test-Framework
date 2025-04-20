package com.listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import extent.utils.ExtentReportManager;
import extent.utils.ExtentTestManager;
import com.utilities.ExcelReportUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Map;

public class ExtentTestListener implements ITestListener {

	private static final ExtentReports extent = ExtentReportManager.getInstance();

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite Finished: " + context.getName());
		extent.flush(); // Flush Extent report
		ExcelReportUtils.saveReport(); // Save Excel report
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		Map<String, String> report = ExcelReportUtils.getReport();

		// Use TestCaseID if present, else fallback to method name
		String testName = "TestCaseID";

		ExtentTest test = extent.createTest(testName);
		ExtentTestManager.setTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest test = ExtentTestManager.getTest();

		Map<String, String> report = ExcelReportUtils.getReport();
		if (!report.get("Result").equalsIgnoreCase("FAIL")) {
			if (report != null) {
				report.put("Result", "PASS");
				ExcelReportUtils.writeRow(report);
				ExcelReportUtils.clear();
			}
			if (test != null) {
				test.log(Status.PASS, "Test Passed");
			}
		} else {
			onTestFailure(result);
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest test = ExtentTestManager.getTest();
		if (test != null) {
			test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
		}

		Map<String, String> report = ExcelReportUtils.getReport();
		if (report != null) {
			report.put("Result", "FAIL");
			report.put("Error", result.getThrowable() != null ? result.getThrowable().getMessage()
					: report.get("Error") != null ? report.get("Error") : "Failed");
			ExcelReportUtils.writeRow(report);
			ExcelReportUtils.clear();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest test = ExtentTestManager.getTest();
		if (test != null) {
			test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
		}

		Map<String, String> report = ExcelReportUtils.getReport();
		if (report != null) {
			report.put("Result", "SKIP");
			report.put("Error", result.getThrowable() != null ? result.getThrowable().getMessage() : "Skipped");
			ExcelReportUtils.writeRow(report);
			ExcelReportUtils.clear();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentTest test = ExtentTestManager.getTest();
		if (test != null) {
			test.log(Status.WARNING, "Test Partially Failed");
		}

		Map<String, String> report = ExcelReportUtils.getReport();
		if (report != null) {
			report.put("Result", "PARTIAL");
			ExcelReportUtils.writeRow(report);
			ExcelReportUtils.clear();
		}
	}
}
