package Assignment;



	
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import io.github.bonigarcia.wdm.WebDriverManager;

	import java.time.Duration;

	public class ValidateSliderValue {
	    public static void main(String[] args) {
	      
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();

	        try {
	       
	            driver.get("https://fitpeo.com/revenue-calculator");

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	            WebElement textField = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//input[@type='number' and @min='0' and @max='2000']")));

	            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	            jsExecutor.executeScript("arguments[0].value = '560';", textField);
	            jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", textField);

	      
	            Thread.sleep(7000); 

	          
	            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//input[@type='range' and @min='0' and @max='2000']")));

	       
	            String sliderValue = slider.getAttribute("value");
	            if ("560".equals(sliderValue)) {
	                System.out.println("Validation Successful: Slider updated to reflect value 560.");
	            } else {
	                System.out.println("Validation Failed: Slider value is " + sliderValue + " instead of 560.");
	            }

	        
	            Thread.sleep(5000);

	        } catch (Exception e) {
	            System.err.println("An error occurred: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	          
	            driver.quit();
	        }
	    }
	}



	


