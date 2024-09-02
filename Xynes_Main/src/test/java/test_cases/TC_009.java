package test_cases;

import org.testng.annotations.Test;

import base_package.Base_Class;
import page_object.Translate_Page_Objects;

public class TC_009 extends Base_Class

{
	Translate_Page_Objects Translate_English;
	
	
  @Test
  public void Test_English() 
  
  {
	  Translate_English= new Translate_Page_Objects (driver);
	  
	  Translate_English.Click_English();
  }

}

