package test_Base;

//import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;



public class Base_Class
{
	public WebDriver driver;
	public WebDriverWait wait;



	    @BeforeClass
	    public void setUp() 

	    {

	        System.setProperty("webdriver.chrome.driver", "/Users/divyanshupriyadarshi/chromedriver-mac-arm64/chromedriver");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.upstatebeertourist.com/public/");
	       driver.findElement(By.id("byear")).sendKeys("1998");
	       driver.findElement(By.name("verify")).click();



}
	    
}