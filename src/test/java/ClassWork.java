import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ClassWork {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void before(){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\HomeTask29\\Files\\chromedriver.exe");

        driver = new ChromeDriver();
        wait = (new WebDriverWait(driver, 10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://rozetka.com.ua");
    }
    @Test

     public void windowHandlesExp () throws InterruptedException {
        WebElement loginBtn = driver.findElement(By.xpath("//a[@class='header-topline__user-link link-dashed']"));
        loginBtn.click();

        WebElement regBtn = driver.findElement(By.xpath("//a[@class='auth-modal__register-link']"));
        regBtn.click();

        WebElement privatePolicyBtn = driver.findElement(By.xpath("//a[@class='button button_type_link']"));
        privatePolicyBtn.click();

        String mainTab = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        TimeUnit.SECONDS.sleep(5);
        driver.switchTo().window(mainTab);
        TimeUnit.SECONDS.sleep(5);
    }
    @AfterMethod
    public void after(){
        driver.quit();
    }
}