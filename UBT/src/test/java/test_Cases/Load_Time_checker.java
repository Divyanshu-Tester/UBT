package test_Cases;

// Import necessary classes for WebDriver, TestNG annotations, and Logger
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.logging.Logger;
import test_Base.Base_Class;

public class Load_Time_checker extends Base_Class {

    // Initialize a Logger instance for logging messages
    private static final Logger LOGGER = Logger.getLogger(Load_Time_checker.class.getName());

    // DataProvider method to supply URLs for the test method
    @DataProvider(name = "urls")
    public Object[][] createData() {
        return new Object[][] {
            {"https://www.upstatebeertourist.com/"}, // URL 1
            {"https://www.upstatebeertourist.com/events"}, // URL 2
            {"https://www.upstatebeertourist.com/login"}, // URL 3
            // Add more URLs as needed
        };
    }

    // Test method to check the load time of each URL
    @Test(dataProvider = "urls")
    public void testPageLoadTime(String url) {
        // Record the start time before loading the URL
        long startTime = System.currentTimeMillis();
        
        // Load the URL in the browser
        driver.get(url);
        
        // Record the end time after the URL has loaded
        long endTime = System.currentTimeMillis();
        
        // Calculate the load time by subtracting start time from end time
        long loadTime = endTime - startTime;

        // Log the URL and its load time
        LOGGER.info("URL: " + url + " - Load Time: " + loadTime + " ms");
    }
}