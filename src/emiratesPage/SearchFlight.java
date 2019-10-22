package emiratesPage;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFlight {
 
	public static WebDriver driver;
	private static WebElement departure;
	private static WebElement arrival;
	private static int today;
	static Actions actions;
	static WebDriverWait wait;
	static Random r;
	static List<WebElement> option;
	static Double depDate;
	private static Logger logger;
	
	public static void initialization() throws InterruptedException {		// browser will be setup and launched here
		  
		PropertyConfigurator.configure("Log4j.properties"); // To configure logger
		setLogger(Logger.getLogger(SearchFlight.class)); // to set a class to log
		 
		System.setProperty("webdriver.chrome.driver", "J:\\Selenium\\chromedriver_win32\\77\\chromedriver.exe");
		driver = new ChromeDriver();
		getLogger().info("Chrome driver is initialized");
		
		driver.get("https://www.emirates.com");
		getLogger().info("Emirates site is opened");
		
		driver.manage().window().maximize();
		getLogger().info("Window is maximized");
		
		driver.manage().deleteAllCookies();
		getLogger().info("Deleted all cookies of browser");
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		getLogger().info("Pageload wait added");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getLogger().info("Implicit wait added");
		
		actions = new Actions(driver); // this will be used later
		getLogger().info("Actions object initialized to select departure / arrival places later");
		
		wait = new WebDriverWait(driver, 90 );
		getLogger().info("Wait added so browser can load properly");
		
		r = new Random();
		getLogger().info("Random object initialized so we can pick random index later");
	}
	
	
	public static void setDeparture() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='js-dropdown dropdown-container selected location-icon-show' and @data-aria-search-id='search-flight-departure' and @data-section='flight']"))); // wait till departure field appears when page launches
		getLogger().info("Wait added until departure field is loaded");
		
		departure = driver.findElement(By.xpath("//div[@class='js-dropdown dropdown-container selected location-icon-show' and @data-aria-search-id='search-flight-departure' and @data-section='flight']"));
		getLogger().info("Access Departure WebElement");
		
		departure.click();
		getLogger().info("1st click on Departure field");
		
		departure.click();
		getLogger().info("2nd click on departure field so dropdown can open");
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getLogger().info("Wait added so departure dropdown can load properly");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"js-dropdown dropdown-container selected location-icon-show show\"]//div[@class='js-origin-dropdown']//div[@class='dropdown']"))); // wait till dropdown for departure appears
		getLogger().info("Wait added until departure field drop down data appears");
		
		departure = driver.findElement(By.xpath("//div[@class=\"js-dropdown dropdown-container selected location-icon-show show\"]//div[@class='js-origin-dropdown']//div[@class='dropdown']"));
		getLogger().info("Access Departure field Dropdown");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		option = departure.findElements(By.tagName("li")); // get data of dropdown
		getLogger().info("Get all the list items in departure field, size is: "+option.size());
		
		int size = option.size();	//size of dropdownlist
		getLogger().info("Departure: Size of list items calculated");
		
		int index = r.nextInt(size);	// randomly select any index
		getLogger().info("Select a random index from the size i.e: "+index);
		
		actions.moveToElement(option.get(index)).click().build().perform(); // to select random index from departure dropdown list
		getLogger().info("Click on random index for Departure Dropdown");
	}
	
	
	public static void setArrival() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='js-dropdown dropdown-container'and @data-aria-search-id='search-flight-arrival'and @data-section='flight']"))); //wait till arrival field appears when page launches
		getLogger().info("Wait added until arrival field is loaded");
		
		arrival = driver.findElement(By.xpath("//div[@class='js-dropdown dropdown-container' and @data-aria-search-id='search-flight-arrival'and @data-section='flight']"));
		getLogger().info("Access Arrival WebElement");
		
//		arrival.click();
		getLogger().info("Click on Arrival field");
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getLogger().info("Wait added so arrival dropdown can load properly");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='js-dropdown dropdown-container show']//div[@class='destination-dropdown']//div[@class='dropdown']")));	// wait till dropdown for departure appears
		getLogger().info("Wait added until arrival field drop down data appears");

		arrival = driver.findElement(By.xpath("//div[@class='js-dropdown dropdown-container show']//div[@class='destination-dropdown']//div[@class='dropdown']"));
		getLogger().info("Access Arrival field Dropdown");
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getLogger().info("Wait added so arrival dropdown can load properly");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		option = arrival.findElements(By.tagName("li")); // get data of dropdown
		getLogger().info("Get all the list items in arrival field, size is: "+option.size());
		
		int size = option.size();		//size of dropdownlist
		getLogger().info("Arrival: Size of list items calculated");
		
		int index = r.nextInt(size);	// randomly select any index
		getLogger().info("Select a random index from the size i.e: "+index);
		
		actions.moveToElement(option.get(index)).click().build().perform();;
		getLogger().info("Click on random index for Arrival Dropdown");
	}
	
	public void selectDepartureDate() {
		
		datePickerTest();
	
		
	}
	
	public void selectArrivalDate() {
		
	
		returningdateTest();
		
	}
	
	public WebElement getDeparturePlace() // get data in driver for Departure Place once selected
	{
		departure = driver.findElement(By.xpath("//div[@data-section='flight']//div//div//input[@class='js-field-input field__input js-dropdown-open field__input--active' and @name='Departure airport']"));
		getLogger().info("Departure place field data accessed");
		return departure;
	}
	
	public WebElement getArrivalPlace() // get data in driver for Departure Place once selected
	{
		arrival = driver.findElement(By.xpath("//div[@data-section='flight']//div//div//input[@class='js-field-input field__input js-dropdown-open field__input--active' and @name='Arrival airport']"));
		getLogger().info("Arrival place field data accessed");
		return arrival;
	}
	
	public WebElement getDepartureDate() // get data in driver for Departure Date once selected
	{
		departure = driver.findElement(By.id("search-flight-date-picker--depart"));
		return departure;
	}
	
	public WebElement getArrivalDate() // get data in driver for Departure Date once selected
	{
		departure = driver.findElement(By.id("search-flight-date-picker--return"));
		return arrival;
	}
	
	
	public static void closeBrowser() {		// browser will be closed here
		    
		driver.quit();
		getLogger().info("Browser is quit");
	  }
	
	private static void returningdateTest() {
	
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	      today = getCurrentDay();	//Get Today's number
	      System.out.println("Today's number: " + today + "\n");

	      driver.findElement(By.xpath("//input[@id='search-flight-date-picker--return']"));	//Click and open the datepickers
	      
	      WebElement dateWidgetFrom = driver.findElement(By.xpath("//eol-calendar[@title='Please choose your departure date']")); //This is from date picker table

	      //This are the columns of the from date picker table
	      List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

	      Double arrDate = Math.floor((Math.random()*((30-depDate)+1))+depDate);
	      int x = (int) arrDate.doubleValue();
	      String s = Integer.toString(x);

	      for (WebElement cell: columns) {
	    	  
	          if (cell.getText().equals(s)) {
	              cell.click();
	              break;
	          }
	      }
	      System.out.println(arrDate+"ArrDate");
	}

	public static void datePickerTest() {
	      //Declare a implicit wait for synchronisation
	      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	      //Get Today's number
	      today = getCurrentDay();
	      System.out.println("Today's number: " + today + "\n");

	      //Click and open the datepickers
	      driver.findElement(By.xpath("//input[@id='search-flight-date-picker--depart']"));
	      //This is from date picker table
	      WebElement dateWidgetFrom = driver.findElement(By.xpath("//eol-calendar[@title='Please choose your departure date']"));

	      //This are the columns of the from date picker table
	      List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

	     
	      depDate = Math.floor((Math.random()*((30-today)+1))+today);
	      int x = (int) depDate.doubleValue();
	      String s = Integer.toString(x);

	      for (WebElement cell: columns) {
	         
	          if (cell.getText().equals(s)) {
	              cell.click();
	              break;
	          }
	      }
	      System.out.println(depDate+"depDate");

	}

	 private static int getCurrentDay (){
		 
	      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());	//Create a Calendar Object
	      getLogger().info("Calendar object created");

	      int todayInt = calendar.get(Calendar.DAY_OF_MONTH);	//Get Current Day as a number
	      getLogger().info("today's date calculated: "+todayInt);

	      String todayStr = Integer.toString(todayInt);	//Integer to String Conversion
	      getLogger().info("today's date converted to string: "+todayStr);

	      return todayInt;
	  }
	 
	 public void failed(String testMethodName) {
		 
		 File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
		 
			 FileUtils.copyFile(sourceFile, new File("C:\\Users\\adnan\\ecl-ws\\EmiratesFlightSearch\\ScreenshotsOfFailedCases\\"+testMethodName+".jpg"));
		 }
		 catch (IOException e){
			 
			 e.printStackTrace();
		 }
	 }

	 public static Logger getLogger() {
		return logger;
	}

	 public static void setLogger(Logger logger) {
		SearchFlight.logger = logger;
	}
}
