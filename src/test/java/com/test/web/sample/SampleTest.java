package com.test.web.sample;

import base.test.BaseTest;
import com.utilities.ExcelReportUtils;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SampleTest extends BaseTest {

    @Test(dataProvider = "excelData")
    public void runTest(HashMap<String, String> data) {
        report.clear();
        report.put("TestCaseID", data.get("TestCaseID"));
        report.put("TestDataID", data.get("TestDataID"));
        report.put("Expected", data.get("Expected"));
        report.put("Result", "FAIL");
        String expected = data.get("Expected");
        String actual = data.get("Username");
        String comments = "";
        String errorMessage = "";

        try {
            System.out.println("Running test for: " + data.get("TestCaseID"));

            report.put("Actual", actual);

            if (expected.equals(actual)) {
                report.put("Result", "PASS");
            } else {
                comments = "Mismatch in expected vs actual.";
                report.put("Comments", comments);
            }

        } catch (Exception e) {
            errorMessage = e.getMessage();
            report.put("Error", errorMessage);
        } finally {
            ExcelReportUtils.writeRow(report);
        }
    }
}
