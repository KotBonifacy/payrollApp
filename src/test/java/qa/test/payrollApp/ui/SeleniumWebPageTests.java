package qa.test.payrollApp.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumWebPageTests {
    public static WebDriver driver;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "selenium-chrome-driver//chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testExample(){
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textInput = driver.findElement(By.name("my-text"));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
