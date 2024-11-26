package Assignment;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ValidateRecurringReimbursement {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public ValidateRecurringReimbursement() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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


    public void extractReimbursementValues() {
        try {
       
            String pageLoadState = (String) jsExecutor.executeScript("return document.readyState");
            if (!"complete".equals(pageLoadState)) {
                System.err.println("[ERROR] Page not fully loaded.");
                return;
            }

     
            Map<String, String> cptXpaths = new HashMap<>();
            cptXpaths.put("CPT-99091", "/html/body/div[1]/div[1]/div[2]/div[1]/label/span[1]/input");
            cptXpaths.put("CPT-99453", "/html/body/div[1]/div[1]/div[2]/div[2]/label/span[1]/input");
            cptXpaths.put("CPT-99454", "/html/body/div[1]/div[1]/div[2]/div[3]/label/span[1]/input");
            cptXpaths.put("CPT-99474", "/html/body/div[1]/div[1]/div[2]/div[8]/label/span[1]/input");

 
            Map<String, String> cptReimbursementValues = new HashMap<>();

       
            for (Map.Entry<String, String> entry : cptXpaths.entrySet()) {
                String cptCode = entry.getKey();
                String checkboxXpath = entry.getValue();

              
                String reimbursementXpath = checkboxXpath.replace("/input", "/following-sibling::span");

                try {
              
                    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(checkboxXpath)));
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkbox);

             
                    WebElement reimbursementElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reimbursementXpath)));

                   
                    String reimbursementText = reimbursementElement.getText().replace("$", "").trim();

            
                    cptReimbursementValues.put(cptCode, reimbursementText);
                    System.out.println("[INFO] " + cptCode + " Reimbursement Value: $" + reimbursementText);

                } catch (TimeoutException e) {
                    System.err.println("[ERROR] Timeout locating reimbursement value for " + cptCode);
                }
            }

            System.out.println("\n[INFO] Extracted Reimbursement Values:");
            cptReimbursementValues.forEach((cptCode, value) -> System.out.println(cptCode + ": $" + value));

        } catch (Exception e) {
            System.err.println("[ERROR] Failed to extract reimbursement values: " + e.getMessage());
        }
    }

    public void tearDown() {
        driver.quit();
        System.out.println("[INFO] Test execution completed.");
    }

 
    public static void main(String[] args) {
        ValidateRecurringReimbursement test = new ValidateRecurringReimbursement();
        try {
            test.navigateToRevenueCalculatorPage(); 
            test.extractReimbursementValues();     
        } finally {
            test.tearDown();                     
        }
    }
}













//	
//	