package base.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import extent.utils.*;
import com.utilities.ExcelReportUtils;
import com.utilities.ExcelUtils;
import org.testng.annotations.*;

import java.util.*;

public class BaseTest {
    protected List<HashMap<String, String>> testData;

    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

//    @BeforeSuite(alwaysRun = true)
//    public void setupExtentReport() {
//        extent = ExtentReportManager.getInstance();
//    }

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        testData = ExcelUtils.getExcelData("E:\\Shivam\\Downloads\\TestFile.xlsx", "Sheet1");

        ExcelReportUtils.getReport().put("TestCaseID", "");
        ExcelReportUtils.getReport().put("TestDataID", "");
        ExcelReportUtils.getReport().put("Expected", "");
        ExcelReportUtils.getReport().put("Actual", "");
        ExcelReportUtils.getReport().put("Result", "FAIL");
        ExcelReportUtils.getReport().put("Failure","");
        ExcelReportUtils.getReport().put("Error", "");
        ExcelReportUtils.getReport().put("Comments", "");
        
        ExcelReportUtils.setHeaders(new ArrayList<>(ExcelReportUtils.getReport().keySet()));
    }

    @DataProvider(name = "excelData")
    public Object[][] provideData() {
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

//    public void createTestNode(String testName) {
//        ExtentTest extentTest = extent.createTest(testName);
//        test.set(extentTest);
//    }
//
//    public ExtentTest getTestNode() {
//        return test.get();
//    }

//    @AfterClass(alwaysRun = true)
//    public void finalizeReport() {
//        ExcelReportUtils.saveReport();
//    }
//
//    @AfterSuite(alwaysRun = true)
//    public void flushExtentReport() {
//        extent.flush();
//    }
}
