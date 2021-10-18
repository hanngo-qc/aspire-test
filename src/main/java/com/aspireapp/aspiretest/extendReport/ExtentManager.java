package com.aspireapp.aspiretest.extendReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

        htmlReporter.config().setTheme(Theme.DARK); //Theme.DARK
        htmlReporter.config().setDocumentTitle("Aspire Testing");
        htmlReporter.config().setReportName("Aspire Automation");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

}
