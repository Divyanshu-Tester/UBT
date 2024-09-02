package test_cases;

import org.testng.annotations.Test;

import base_package.Base_Class;
import page_object.Translate_Page_Objects;

public class TC_011 extends Base_Class

{
 Translate_Page_Objects Translate_Espanol ;	
	
  @Test
  public void test_Espanol() 
  
  
  {
	  
	  Translate_Espanol= new Translate_Page_Objects(driver);
	  Translate_Espanol.Click_Espanol();
  }
}
