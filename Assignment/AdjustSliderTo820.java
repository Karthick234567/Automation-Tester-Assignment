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

public class AdjustSliderTo820 {
    public static void main(String[] args) {
       
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

     
        driver.manage().window().maximize();

        try {
       
            driver.get("https://fitpeo.com/revenue-calculator");

       
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='number' and @min='0' and @max='2000']")));

         
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].value = '820';", textBox);
            jsExecutor.executeScript(
                "arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));",
                textBox);

    
            String enteredValue = textBox.getAttribute("value");
            if ("820".equals(enteredValue)) {
                System.out.println("Successfully set the value to: " + enteredValue);
            } else {
                System.out.println("Failed to set value. Current value: " + enteredValue);
            }

        } catch (Exception e) {

            System.err.println("An error occurred: " + e.getMessage());
        } finally {
       
            driver.quit();
        }
    }
}




































