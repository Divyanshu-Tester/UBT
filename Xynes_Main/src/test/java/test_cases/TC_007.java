package test_cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base_package.Base_Class;

public class TC_007 extends Base_Class


{
  @Test
  public void Chceck_Research_Page_Title() 
  
  
  {
	  driver.findElement(By.xpath("//a[normalize-space()='Research']")).click();
	  System.out.println("CURENT PAGE TITLE IS : "+driver.getTitle());
	  String Research_Page_Title = driver.getTitle();
	  if(Research_Page_Title=="Research")
	  {
		  System.out.println("PAGE TITLE MATCHED");
	  
	  }
	  else
	  {
		  System.out.println("PAGE TITLE NOT MATCHED RAISE HE BUG :");
	  }
	  
	  }
  }

