package test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Universal 

{
	WebDriver driver;
  @Test
  public void Launch_Browser() 
  
  {
	     System.setProperty("webdriver.chrome.driver", "/Users/divyanshupriyadarshi/chromedriver-mac-arm64/chromedriver");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.upstatebeertourist.com/public/");
	        driver.findElement(By.id("byear")).sendKeys("1998");
	        driver.findElement(By.name("verify")).click();

	  
  }
}
