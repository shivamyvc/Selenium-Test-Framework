package extent.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.FileInputStream;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    public static ExtentReports getInstance() {
        if (extent == null) {
            try {
                // Load config properties
                Properties config = new Properties();
                config.load(new FileInputStream(CONFIG_PATH));

                String reportPath = config.getProperty("report.path", "reports/");
                String testerName = config.getProperty("tester.name", "Unknown");
                String environment = config.getProperty("environment", "QA");

                // Create a timestamped file name
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String fullPath = reportPath + "ExtentReport_" + timeStamp + ".html";

                ExtentSparkReporter reporter = new ExtentSparkReporter(fullPath);
                reporter.config().setReportName("Automation Test Report");
                reporter.config().setDocumentTitle("Execution Report");

                extent = new ExtentReports();
                extent.attachReporter(reporter);
                extent.setSystemInfo("Tester", testerName);
                extent.setSystemInfo("Environment", environment);
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                extent.setSystemInfo("Java Version", System.getProperty("java.version"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return extent;
    }
}

