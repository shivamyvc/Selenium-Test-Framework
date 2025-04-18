package com.extentreport.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("E:\\Shivam\\Downloads\\ExtentReport.html");
            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Shivam Yadav");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
