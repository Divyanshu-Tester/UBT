package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Translate_Page_Objects 

{
	WebDriver driver;
	public Translate_Page_Objects(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	// RE-USABILITY METHOD TO CHANGE LANGUAGE TO ENGLISH
public void Click_English()

{
	driver.findElement(By.xpath("//p[@class='sc-gEvDqW kXBBij translateButton_text__MT2Fu']")).click();
}

// RE-USABILITY METHOD TO CHANGE LANGUAGE TO ESPANOL

public void Click_Espanol()

{
	driver.findElement(By.xpath("//p[normalize-space()='Español']")).click();
}

// RE-USABILITY METHOD TO CHANGE LANGUAGE TO FRENCH

public void Click_French()

{
	driver.findElement(By.xpath("//p[normalize-space()='Français']")).click();
}

// RE-USABILITY METHOD TO CHANGE LANGUAGE TO Japanese


public void Click_Japanese()

{
	driver.findElement(By.xpath("//p[normalize-space()='Français']")).click();
}

	
}



