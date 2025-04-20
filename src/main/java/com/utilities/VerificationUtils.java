package com.utilities;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import java.util.Map;
import org.testng.asserts.SoftAssert;

public class VerificationUtils {

    private final ExtentTest test;
    private final SoftAssert softAssert;

    public VerificationUtils(ExtentTest test) {
        this.test = test;
        this.softAssert = new SoftAssert();
    }

    public void verifyEquals(String actual, String expected, String verificationPointName) {
        boolean isPass = actual.equals(expected);
        String resultIcon = isPass ? "✅" : "❌";
        String actualColor = isPass ? "#d4edda" : "#f8d7da"; // Green or Red background

        String htmlLog = "<div style='border:1px solid #ccc; padding:10px; border-radius:5px;'>"
                + "<b>Verification Point:</b> " + verificationPointName + "<br>"
                + "<span style='background-color:#e6ffe6; padding:4px 8px; border-radius:4px;'>"
                + "<b>Expected:</b> " + expected + "</span><br>"
                + "<span style='background-color:" + actualColor + "; padding:4px 8px; border-radius:4px;'>"
                + "<b>Actual</b>: " + actual + " " + resultIcon + "</span></div>";

        if (isPass) {
            test.log(Status.PASS, htmlLog);
            ExcelReportUtils.getReport().put("Result", "PASS");
        } else {
            test.log(Status.FAIL, htmlLog);
            ExcelReportUtils.getReport().put("Result", "FAIL");
            ExcelReportUtils.getReport().put("Failure", verificationPointName);
        }

        softAssert.assertEquals(actual, expected, verificationPointName);
    }

    public void verifyTrue(boolean condition, String verificationPointName) {
        String resultIcon = condition ? "✅" : "❌";
        String bgColor = condition ? "#d4edda" : "#f8d7da"; // Green or Red

        String htmlLog = "<div style='border:1px solid #ccc; padding:10px; border-radius:5px;'>"
                + "<b>Verification Point:</b> " + verificationPointName + "<br>"
                + "<span style='background-color:" + bgColor + "; padding:4px 8px; border-radius:4px;'>"
                + "Condition: " + (condition ? "True" : "False") + " " + resultIcon + "</span></div>";

        if (condition) {
            test.log(Status.PASS, htmlLog);
            ExcelReportUtils.getReport().put("Result", "PASS");
        } else {
            test.log(Status.FAIL, htmlLog);
            ExcelReportUtils.getReport().put("Result", "FAIL");
            ExcelReportUtils.getReport().put("Failure", verificationPointName);
        }

        softAssert.assertTrue(condition, verificationPointName);
    }

    public void verifyAll() {
        softAssert.assertAll(); // Should be called at the end of test method
    }
}
