package base_package;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import page_object.Translate_Page_Objects;


public class Base_Class 

{
	protected WebDriver driver;
	protected WebDriverWait wait;
	public Translate_Page_Objects Translate;
	

	@BeforeClass
	public void launch() throws InterruptedException
	{
		System.setProperty("webdriver.chromedriver.driver","/Users/divyanshupriyadarshi/chromedriver-mac-arm64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(18));
		driver.get("http://xynes.com:3003/");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='header_logo__HIgPS']")));
		
		
				
		
		
		
	}
	
  

  
  @AfterClass
  public void close()
  {
	  //driver.quit();
  }
}
