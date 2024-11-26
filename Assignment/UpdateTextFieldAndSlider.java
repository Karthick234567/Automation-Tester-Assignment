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

public class UpdateTextFieldAndSlider {
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
            jsExecutor.executeScript("arguments[0].value = '0';", textField);
            jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", textField);

         
            Thread.sleep(5000); 


            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@type='range' and @min='0' and @max='2000']")));

      
            jsExecutor.executeScript("arguments[0].value = '560';", slider);
            jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", slider);

            Thread.sleep(9000);

   
            String sliderValue = slider.getAttribute("value");
            if ("560".equals(sliderValue)) {
                System.out.println("Slider successfully updated to: " + sliderValue);
            } else {
                System.out.println("Failed to update slider. Current slider value: " + sliderValue);
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
       
            driver.quit();
        }
    }
}



























//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//import java.time.Duration;
//
//public class UpdateTextFieldAndSlider {
//    public static void main(String[] args) {
//        // Set up ChromeDriver using WebDriverManager
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        
//
//        try {
//            // Navigate to the Revenue Calculator page
//            driver.get("https://fitpeo.com/revenue-calculator");
//
//            // Wait for the text field to load
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//            WebElement textField = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//input[@type='number' and @min='0' and @max='2000']")));
//
//            // Set the value to 560 in the text field
//            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//            jsExecutor.executeScript("arguments[0].value = '560';", textField);
//            jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", textField);
//
//            // Locate the slider element
//            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]/input")));
//
//            // Update the slider value to match the text field value
//            jsExecutor.executeScript("arguments[0].value = '560';", slider);
//            jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", slider);
//
//            // Validate the slider value
//            String sliderValue = slider.getAttribute("value");
//            if ("560".equals(sliderValue)) {
//                System.out.println("Slider successfully updated to: " + sliderValue);
//            } else {
//                System.out.println("Failed to update slider. Current slider value: " + sliderValue);
//            }
//
//        } catch (Exception e) {
//            System.err.println("An error occurred: " + e.getMessage());
//        } finally {
//            // Quit the driver
//            driver.quit();
//        }
//    }
//}
//
//



















