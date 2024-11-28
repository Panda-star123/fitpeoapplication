package organizationcode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BaseClass {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        // Initialize the class-level driver
        driver = new ChromeDriver();
        driver.get("https://www.fitpeo.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void Testcase() throws InterruptedException {
        // Use the class-level driver instance
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement revenueCalculator = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Revenue Calculator']"))
        );
        revenueCalculator.click();
        //scroll element

        WebElement scroll = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Medicare Eligible Patients']"))
        );
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scroll);

        WebElement slider1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-index='0']"))
        );

        // Instantiate Actions class
        Actions actions = new Actions(driver);

        // Drag the slider to the desired position
        actions.dragAndDropBy(slider1, 94, 0).build().perform();


        //enter the value in textfield
        WebElement  typefield =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='number']")));
        Thread.sleep(5000);
        typefield.click();
        typefield.clear();
        typefield.sendKeys("560");
        Thread.sleep(5000);
        System.out.println("completed");
//
//
////        wait.until(ExpectedConditions.attributeToBe(slider, "value", "560"));
////
////        if ("560".equals(slider)) {
////            System.out.println("Test passed: Slider value is correctly set to 560.");
////        } else {
////            System.out.println("Test failed: Slider value is not correctly set.");
////        }
//
//      //selecting cpt code
//        WebElement scrollelement = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='RPM']"))
//        );
//        JavascriptExecutor js2 = (JavascriptExecutor) driver;
//        js2.executeScript("arguments[0].scrollIntoView(true);", scrollelement);
//        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.cssSelector("body > div.MuiBox-root.css-3f59le > div.MuiBox-root.css-rfiegf > div.MuiBox-root.css-1p19z09 > div:nth-child(1) > label > span.MuiButtonBase-root.MuiCheckbox-root.MuiCheckbox-colorPrimary.MuiCheckbox-sizeMedium.PrivateSwitchBase-root.MuiCheckbox-root.MuiCheckbox-colorPrimary.MuiCheckbox-sizeMedium.MuiCheckbox-root.MuiCheckbox-colorPrimary.MuiCheckbox-sizeMedium.css-1sp9p8c > input")
//        ));
//        try {
//            checkbox.click();
//        } catch (Exception e) {
//            // Use JavaScript click as fallback
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
//        }


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
