package base.test;

import com.utilities.ExcelReportUtils;
import com.utilities.ExcelUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import java.util.*;

public class BaseTest {

    protected Map<String, String> report = new LinkedHashMap<>();
    protected List<HashMap<String, String>> testData;

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        // Read Excel test data
        testData = ExcelUtils.getExcelData("E:\\Shivam\\Downloads\\TestFile.xlsx", "Sheet1");

        // Prepare report structure (headers)
        report.put("TestCaseID", "");
        report.put("TestDataID", "");
        report.put("Expected", "");
        report.put("Actual", "");
        report.put("Result", "FAIL");
        report.put("Error", "");
        report.put("Comments", "");

        ExcelReportUtils.setHeaders(new ArrayList<>(report.keySet()));
    }

    @DataProvider(name = "excelData")
    public Object[][] provideData() {
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @AfterClass(alwaysRun = true)
    public void finalizeReport() {
        ExcelReportUtils.saveReport();
    }
}
