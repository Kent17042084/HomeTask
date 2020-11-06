import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class Test29 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\HomeTask29\\Files\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = (new WebDriverWait(driver, 10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //1. Открыть https://rozetka.com.ua/
        driver.get("https://rozetka.com.ua");
    }

    @Test
    public void test() throws IOException {

        //2. Ввести в поисковый инпут Mac.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement SearchInput = driver.findElement(By.xpath("//input[@name='search']"));
        SearchInput.sendKeys("Mac");

        //3. Кликнуть по кнопке поиск.

        WebElement SearchButton = driver.findElement(By.xpath("//button[contains(text(), 'Найти')]"));
        SearchButton.click();

        //4. На странице поисковой выдачи собрать все тайтлы(название товаров) и цены товаров.
        wait = (new WebDriverWait(driver, 10));
        Map<String, String> productTitlePrice = new LinkedHashMap<String, String>();

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='goods-tile']"));
        for (WebElement el : products) {
            String title = el.findElement(By.className("goods-tile__title")).getText();
            String price = el.findElement(By.className("goods-tile__price-value")).getText();

            //5. Записать их в Map.
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = (new WebDriverWait(driver, 5));
            productTitlePrice.put(title, price);
        }

        //System.out.println(productTitlePrice.size());
        //System.out.println(productTitlePrice.toString());

        //6. С помощью FileWriter записать данные с Map в файл rozetkaTest.txt
        FileWriter writer = new FileWriter("rozetkaTest.txt");
        writer.write(productTitlePrice.toString());
        writer.close();
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}