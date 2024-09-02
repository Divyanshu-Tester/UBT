package test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Find_Map_Error 
{

    private WebDriver driver;
    private DevTools devTools;

    @BeforeClass
    public void setUp() {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/divyanshupriyadarshi/chromedriver-mac-arm64/chromedriver");

        // Initialize ChromeDrivers
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Set up DevTools for network capture
        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Set a timeout to handle delays
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testApiCall() 
    {
        // Navigate to the web application
        driver.get("https://www.upstatebeertourist.com/");
        driver.findElement(By.id("byear")).sendKeys("1998");
        driver.findElement(By.name("verify")).click();

        // Capture and print browser console logs
        captureBrowserLogs();

        // Start listening for network responses
        devTools.addListener(Network.responseReceived(), response -> 
        {
            String url = response.getResponse().getUrl();
            int status = response.getResponse().getStatus();
            System.out.println("Received response: " + url + " with status " + status);

            // Check for API call success
            if (url.contains("/api/") && status == 200) 
            {
                System.out.println("API call successful: " + url);
            } else if (url.contains("/api/") && status != 200) 
            {
                System.out.println("API call failed with status: " + status);
            }
        });

        // Interact with the page to trigger the API call
        WebElement triggerButton = driver.findElement(By.id("triggerButton"));
        triggerButton.click();
    }

    private void captureBrowserLogs() {
        LogEntries logs = driver.manage().logs().get("browser");
        for (LogEntry logEntry : logs) {
            System.out.println("Browser Console Log: " + logEntry.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
