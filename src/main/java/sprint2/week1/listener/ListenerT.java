package sprint2.week1.listener;


import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static sprint2.week1.base.BaseClass.takeScreenShot;

public class ListenerT implements ITestListener, IInvokedMethodListener {

    public String methodName;
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        methodName = method.getTestMethod().getMethodName();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        try {

            System.out.println("Test failed for "+methodName);
            takeScreenShot(methodName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }
}
