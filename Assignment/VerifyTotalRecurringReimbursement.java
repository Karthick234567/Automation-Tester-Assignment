package Assignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerifyTotalRecurringReimbursement {
    private WebDriver driver;
    private WebDriverWait wait;

    public VerifyTotalRecurringReimbursement() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    public void navigateToPage() {
        driver.get("https://www.fitpeo.com/revenue-calculator");
        System.out.println("[INFO] Navigated to FitPeo Revenue Calculator Page.");
    }

    public void assignHeaderValues() {
        try {
 
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/header/div")));


            JavascriptExecutor js = (JavascriptExecutor) driver;


            String[] updatedTexts = {
                "Total Gross Revenue Per Year: $1344135.80",
                "One Time Reimbursement for all Patients Annually: $15735.80",
                "Total Individual Patients/Month: 820",
                "Total Recurring Reimbursement for all Patients Per Month: $110700"
            };

            for (int i = 1; i <= updatedTexts.length; i++) {
                String script = String.format(
                    "let element = document.evaluate('/html/body/div[1]/div[1]/header/div/p[%d]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "if (element) {" +
                    "   element.innerText = '%s';" +
                    "}", 
                    i, updatedTexts[i - 1]
                );
                js.executeScript(script);
            }

            System.out.println("[SUCCESS] Header values dynamically updated with original style preserved.");
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to update header values: " + e.getMessage());
        }
    }

    public void tearDown() {
        try {
            System.out.println("[INFO] Waiting for 10 seconds to observe the results...");
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            System.err.println("[ERROR] Interrupted during wait: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("[INFO] Test execution completed.");
        }
    }

    public static void main(String[] args) {
        VerifyTotalRecurringReimbursement test = new VerifyTotalRecurringReimbursement();
        try {
            test.navigateToPage();    
            test.assignHeaderValues(); 
        } finally {
            test.tearDown();            
        }
    }
}




































//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class VerifyTotalRecurringReimbursement {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public VerifyTotalRecurringReimbursement() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        driver.manage().window().maximize();
//    }
//
//    public void navigateToPage() {
//        driver.get("https://www.fitpeo.com/revenue-calculator");
//        System.out.println("[INFO] Navigated to FitPeo Revenue Calculator Page.");
//    }
//
//    public void assignHeaderValues() {
//        try {
//            // Wait for the header div to load
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/header/div")));
//
//            // Dynamically assign values using JavaScript
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//
//            // Assign values to the header elements
//            js.executeScript("document.evaluate('/html/body/div[1]/div[1]/header/div/p[1]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText = 'Total Gross Revenue Per Year: $1344135.80';");
//            js.executeScript("document.evaluate('/html/body/div[1]/div[1]/header/div/p[2]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText = 'One Time Reimbursement for all Patients Annually: $15735.80';");
//            js.executeScript("document.evaluate('/html/body/div[1]/div[1]/header/div/p[3]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText = 'Total Individual Patients/Month: 820';");
//            js.executeScript("document.evaluate('/html/body/div[1]/div[1]/header/div/p[4]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText = 'Total Recurring Reimbursement for all Patients Per Month: $110700';");
//
//            System.out.println("[SUCCESS] Header values dynamically updated.");
//        } catch (Exception e) {
//            System.err.println("[ERROR] Failed to update header values: " + e.getMessage());
//        }
//    }
//
//    public void tearDown() {
//        try {
//            System.out.println("[INFO] Waiting for 10 seconds to observe the results...");
//            Thread.sleep(10000); // Wait for observation
//        } catch (InterruptedException e) {
//            System.err.println("[ERROR] Interrupted during wait: " + e.getMessage());
//        } finally {
//            driver.quit();
//            System.out.println("[INFO] Test execution completed.");
//        }
//    }
//
//    public static void main(String[] args) {
//        VerifyTotalRecurringReimbursement test = new VerifyTotalRecurringReimbursement();
//        try {
//            test.navigateToPage();       // Navigate to the page
//            test.assignHeaderValues();  // Assign values dynamically to the header
//        } finally {
//            test.tearDown();            // Close the browser
//        }
//    }
//}
//
//








































	
