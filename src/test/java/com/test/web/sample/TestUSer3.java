package com.test.web.sample;

import base.test.BaseTest;
import com.utilities.ExcelReportUtils;
import com.utilities.VerificationUtils;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestUSer3 extends BaseTest {
	
	

    @Test(dataProvider = "excelData")
    public void runTest(HashMap<String, String> data) {
    	
    	
        report.clear();
        report.put("TestCaseID", data.get("TestCaseID"));
        report.put("TestDataID", data.get("TestDataID"));
        report.put("Expected", data.get("Expected"));
        report.put("Result", "FAIL");
        String expected = data.get("Expected");
        String actual = data.get("Username");
        String errorMessage = "";
        
        createTestNode(data.get("TestCaseID"));
        getTestNode().info(data.get("Username"));
        VerificationUtils VP= new VerificationUtils(getTestNode(),report);

        try {
            System.out.println("Running test for: " + data.get("TestCaseID"));

            report.put("Actual", actual);
             
            VP.verifyEquals(actual, expected, "Verifying Usename");
            VP.verifyEquals(actual+" Hello", expected+" Hello", "Verifying Password");
        } catch (Exception e) {
            errorMessage = e.getMessage();
            report.put("Error", errorMessage);
            getTestNode().fail("Exception occurred: " + errorMessage);
        } finally {
            ExcelReportUtils.writeRow(report);
            
        }
    }
    

}
