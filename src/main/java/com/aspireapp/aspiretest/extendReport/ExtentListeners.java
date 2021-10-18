package com.aspireapp.aspiretest.extendReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Date;

public class ExtentListeners implements ITestListener {

    private static final Logger logger = Logger.getLogger(ExtentListeners.class);

    static Date d = new Date();
    public static String extentReportName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
    String testFeature;

    private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir") + "/reports/" + extentReportName);

    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(testFeature + " > " + result.getName());
        testReport.set(test);
    }

    public void onTestSuccess(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + methodName.toUpperCase() + " => PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(m);
    }

    public void onTestFailure(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + methodName.toUpperCase() + " => FAILED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        testReport.get().log(Status.FAIL, m);

        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                + "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");
    }

    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        testReport.get().skip(m);

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        testFeature = context.getName();
    }

    public void onFinish(ITestContext context) {

        if (extent != null) {
            extent.flush();
        }
    }


}
