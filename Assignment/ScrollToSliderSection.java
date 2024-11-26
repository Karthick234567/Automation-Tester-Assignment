package Assignment;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScrollToSliderSection {
    public static void main(String[] args) {
     
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        try {
         
            driver.get("https://fitpeo.com/revenue-calculator");
            driver.manage().window().maximize();
            System.out.println("[INFO] Navigated to Revenue Calculator Page.");

  
            String sliderXPath = "//input[@type='range' and @aria-valuemax='2000']";

  
            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sliderXPath)));

 
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", slider);

            String targetValue = "1000";
            jsExecutor.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", slider, targetValue);
            System.out.println("[INFO] Slider value set to " + targetValue);

            String currentValue = slider.getAttribute("value");
            if (currentValue.equals(targetValue)) {
                System.out.println("[INFO] Slider value correctly reflects " + targetValue);
            } else {
                System.err.println("[ERROR] Slider value mismatch: Expected " + targetValue + ", but got " + currentValue);
            }

        } catch (Exception e) {
            System.err.println("[ERROR] Exception occurred: " + e.getMessage());
        } finally {
      
            driver.quit();
            System.out.println("[INFO] Browser closed.");
        }
    }
}

























