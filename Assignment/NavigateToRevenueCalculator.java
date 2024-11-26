package Assignment;


	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class NavigateToRevenueCalculator {

	    private WebDriver driver;

	 
	    public NavigateToRevenueCalculator() {
	        WebDriverManager.chromedriver().setup(); 
	        driver = new ChromeDriver();
	        driver.manage().window().maximize(); 
	    }

	    public void navigateToRevenueCalculatorPage() {
	        try {
	            String revenueCalculatorUrl = "https://fitpeo.com/revenue-calculator"; 
	            driver.get(revenueCalculatorUrl); 
	            System.out.println("[INFO] Navigated to the Revenue Calculator Page.");
	        } catch (Exception e) {
	            System.err.println("[ERROR] Failed to navigate to the Revenue Calculator Page: " + e.getMessage());
	        }
	    }

	  
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("[INFO] Browser closed.");
	        }
	    }

	  
	    public static void main(String[] args) {
	        NavigateToRevenueCalculator test = new NavigateToRevenueCalculator();
	        try {
	            test.navigateToRevenueCalculatorPage(); 
	        } finally {
	            test.tearDown(); 
	        }
	    }
	}


