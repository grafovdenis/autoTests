import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private JavascriptExecutor jsx;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://ok.ru/";
        jsx = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCase() throws Exception {
        driver.get(baseUrl + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
        driver.findElement(By.id("field_email")).clear();
        driver.findElement(By.id("field_email")).sendKeys("technopolisbot");
        driver.findElement(By.id("field_password")).clear();
        driver.findElement(By.id("field_password")).sendKeys("technopolis16");
        driver.findElement(By.cssSelector("div.form-actions > div > input.button-pro.__wide")).click();

        driver.findElement(By.cssSelector("#hook_Block_Navigation > div > div > a:nth-child(6) > div > div")).click();
        driver.navigate().to(driver.getCurrentUrl() + "/mine");
        System.out.println(driver.getCurrentUrl());

        jsx.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(1000);
        assertEquals(driver.findElements(By.linkText("Group")).size() ,86);
//        driver.findElement(By.cssSelector("span.add-stub_tx")).click();
//        driver.findElement(By.cssSelector("div.create-group-dialog_tx")).click();
//        driver.findElement(By.id("field_name")).clear();
//        driver.findElement(By.id("field_name")).sendKeys("Group");
//        driver.findElement(By.id("hook_FormButton_button_create")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}