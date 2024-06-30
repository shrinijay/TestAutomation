package sprint2.week1.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedCases implements IRetryAnalyzer {
    int i=1;
    @Override
    public boolean retry(ITestResult iTestResult) {

        if(i < 2)
        {
            i++;
            System.out.println("came here in retry analyzer");
            return true; //true means the testcase has failed
        }

        return false; // false -> testcase passed
    }
}
