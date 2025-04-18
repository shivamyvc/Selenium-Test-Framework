package com.utilities;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

import java.util.Map;

import org.testng.asserts.SoftAssert;

public class VerificationUtils {

    private final ExtentTest test;
    private final SoftAssert softAssert;
    private final Map<String, String> report;

    // Constructor to inject ExtentTest instance
    public VerificationUtils(ExtentTest test, Map<String, String> report) {
        this.test = test;
        this.softAssert = new SoftAssert();
		this.report = report;
    }

    public void verifyEquals(String actual, String expected, String verificationPointName) {
        if (actual.equals(expected)) {
            test.log(Status.PASS, verificationPointName + " | Expected: " + expected + ", Actual: " + actual);
            report.put("Result", "PASS");
        } else {
            test.log(Status.FAIL, verificationPointName + " | Expected: " + expected + ", Actual: " + actual);
            report.put("Result", "FAIL");
        }
        softAssert.assertEquals(actual, expected, verificationPointName);
    }

    public void verifyTrue(boolean condition, String verificationPointName) {
        if (condition) {
            test.log(Status.PASS, verificationPointName);
            report.put("Result", "PASS");
        } else {
            test.log(Status.FAIL, verificationPointName);
            report.put("Result", "FAIL");
        }
        softAssert.assertTrue(condition, verificationPointName);
    }

    public void verifyAll() {
        softAssert.assertAll(); // Should be called at the end of test method
    }
}
