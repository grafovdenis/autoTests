package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {
    WebDriver driver;
    String baseUrl;
    JavascriptExecutor jsx;

    BasePage(WebDriver driver) {
        this.driver = driver;
        baseUrl = "https://ok.ru";
        jsx = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    abstract void check(WebDriver driver);
}
