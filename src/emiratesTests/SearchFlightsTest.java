package emiratesTests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test; // test Annotation Library

import emiratesPage.SearchFlight;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

@Listeners(CustomListener.class)
public class SearchFlightsTest extends SearchFlight{
	
	//	int departure = 0; // to create random departure date
	//	int arrival = 0; 	// to create random arrival date
	//	Random r = new Random();
	//	String textInsideInputField;
	//	WebElement departure1;
	//	WebElement arrival1;
  
 @Test(priority = 1)
  public void setUp() throws InterruptedException {
	  
	 	initialization();	// Browser setup has been called here from base class
	 	getLogger().info("Setup has been Completed");
  }

  @Test(priority = 2)
  public void launchSite()		// this is to test if correct website is launched
  {
		getLogger().info("LaunchSite has been started");
		Assert.assertEquals(driver.getTitle(), "Emirates flights – Book a flight, browse our flight offers and explore the Emirates Experience");
		getLogger().info("LaunchSite has been Completed");

  }
  
  @Test(priority = 3)
  public void testDeparture() {
	  
		getLogger().info("setDeparture has been started");
	    setDeparture();		// set departure place
	    
	    WebElement departure1 = getDeparturePlace(); // get departure value after successful update- returns a WebElement
		getLogger().info("Departure Place has been stored in WebElement");

	    String textInsideInputField = departure1.getAttribute("value"); // getting data from WebElement
		getLogger().info("Fetched value of WebElement departure1");
	    
	    Assert.assertTrue(!textInsideInputField.isEmpty()); // Assertion added if there is any data in Departure, pass the case
		getLogger().info("Assertion checked if there is any value in field");

  }
  
  @Test(priority = 4)
  public void testArrival() {
	  
	  	getLogger().info("setArrival has been started");
	  	setArrival();
	  	
	  	WebElement arrival1 = getArrivalPlace(); // get arrival value after successful update- returns a WebElement
	  	getLogger().info("Arrival Place has been stored in WebElement");
	  	
	  	String textInsideInputField = arrival1.getAttribute("value"); // getting data from WebElement
	  	getLogger().info("Fetched value of WebElement arrival1");
	  	
	    Assert.assertTrue(!textInsideInputField.isEmpty()); // Assertion added if there is any data in Arrival, pass the case
	    getLogger().info("Assertion checked if there is any value in field");
  }
  
  @Test(priority = 5)
  public void testDepartureDate() {
	  
	  	getLogger().info("setDepartureDate has been started");
	    selectDepartureDate();		// set departure date
	    
	    WebElement departure1 = getDepartureDate(); // get departure value after successful update- returns a WebElement
		getLogger().info("Departure Date has been stored in WebElement");
		
		String textInsideInputField = departure1.getAttribute("value"); // getting data from WebElement
		getLogger().info("Fetched value of WebElement departure1");
		    
		Assert.assertTrue(!textInsideInputField.isEmpty()); // Assertion added if there is any data in Departure, pass the case
		getLogger().info("Assertion checked if there is any value in field");
	  
  }
  
  @Test(priority = 6)
  public void testArrivalDate() {
	  
	  	getLogger().info("testArrivalDate has been started");
	  	selectArrivalDate();
	  	
	  	WebElement arrival1 = getArrivalDate(); // get arrival value after successful update- returns a WebElement
	  	getLogger().info("Arrival Place has been stored in WebElement");
	  	
	  	String textInsideInputField = arrival1.getAttribute("value"); // getting data from WebElement
	  	getLogger().info("Fetched value of WebElement arrival1");
	  	
	    Assert.assertTrue(textInsideInputField.isEmpty()); // Assertion added if there is any data in Arrival, pass the case
	    getLogger().info("Assertion checked if there is any value in field");
	  
  }
  
  @Test(priority = 7)
  public void tearDown() {
	  
	  
	  closeBrowser(); //This is will close browser , called here from base class
	  getLogger().info("Browser is closed");
  }
  
 

}
