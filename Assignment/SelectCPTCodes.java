package Assignment;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectCPTCodes {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public SelectCPTCodes() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }


    public void navigateToRevenueCalculatorPage() {
        try {
            driver.get("https://fitpeo.com/revenue-calculator");
            System.out.println("[INFO] Navigated to FitPeo Revenue Calculator Page.");
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to navigate to FitPeo Revenue Calculator Page: " + e.getMessage());
        }
    }


    public void selectCPTCodes() {
        try {
            String pageLoadState = (String) jsExecutor.executeScript("return document.readyState");
            if (!"complete".equals(pageLoadState)) {
                System.err.println("[ERROR] Page not fully loaded.");
                return;
            }

       
            String[] xpaths = {
                "/html/body/div[1]/div[1]/div[2]/div[1]/label/span[1]/input",
                "/html/body/div[1]/div[1]/div[2]/div[2]/label/span[1]/input",
                "/html/body/div[1]/div[1]/div[2]/div[3]/label/span[1]/input",
                "/html/body/div[1]/div[1]/div[2]/div[8]/label/span[1]/input"
            };

          
            for (String xpath : xpaths) {
                try {
                  
                    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkbox);

       
                    if (!checkbox.isSelected()) {
                        jsExecutor.executeScript("arguments[0].click();", checkbox);
                        System.out.println("[INFO] Selected checkbox for XPath: " + xpath);
                    } else {
                        System.out.println("[INFO] Checkbox already selected for XPath: " + xpath);
                    }
                    Thread.sleep(3000);
                } catch (TimeoutException e) {
                    System.err.println("[ERROR] Timeout locating checkbox for XPath: " + xpath);
                }
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to select CPT codes: " + e.getMessage());
        }
    }

    public void tearDown() {
        driver.quit();
        System.out.println("[INFO] Test execution completed.");
    }

    public static void main(String[] args) {
        SelectCPTCodes test = new SelectCPTCodes();
        try {
            test.navigateToRevenueCalculatorPage(); 
            test.selectCPTCodes();                
        } finally {
            test.tearDown();                     
        }
    }
}
