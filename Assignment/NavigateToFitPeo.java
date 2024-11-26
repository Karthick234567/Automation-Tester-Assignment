package Assignment;


	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class NavigateToFitPeo {
	    private WebDriver driver;

	
	    public NavigateToFitPeo() {
	        WebDriverManager.chromedriver().setup(); 
	        driver = new ChromeDriver();
	        driver.manage().window().maximize(); 
	    }

	   
	    public void navigateToHomepage() {
	        try {
	            String url = "https://www.fitpeo.com/"; 
	            driver.get(url);
	            System.out.println("[INFO] Navigated to FitPeo Homepage.");
	        } catch (Exception e) {
	            System.err.println("[ERROR] Failed to navigate to FitPeo Homepage: " + e.getMessage());
	        }
	    }

	  
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("[INFO] Browser closed.");
	        }
	    }

	   
	    public static void main(String[] args) {
	        NavigateToFitPeo test = new NavigateToFitPeo();
	        try {
	            test.navigateToHomepage(); 
	        } finally {
	            test.tearDown(); 
	        }
	    }
	}


