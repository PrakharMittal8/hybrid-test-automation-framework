package com.automation.listeners;

import com.automation.utils.*;
import com.aventstack.extentreports.*;

import org.testng.*;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test =
                extent.createTest(
                        result.getMethod().getMethodName()
                );
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test Passed");
        
    }

    @Override
public void onTestFailure(ITestResult result) {

    extentTest.get().fail(result.getThrowable());

    String path =
            ScreenshotUtil.capture(
                    result.getName()
            );

    try {
        if (path != null) {
            extentTest.get()
                    .addScreenCaptureFromPath(path);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}