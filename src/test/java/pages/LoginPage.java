package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginPage extends BasePage {
    private Map<String, String> user = readConfig();

    private String userEmail = user.get("email");
    private String userPassword = user.get("password");

    private String loginLocator = "//input[@name='st.email']";
    private String passwordLocator = "//input[@name='st.password']";
    private String submitLocator = "//input[@data-l='t,sign_in']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private Map<String, String> readConfig() {
        Map<String, String> result = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/user.ini"));
            String line = reader.readLine();
            while (line != null) {
                String[] splitted = line.split("=");
                result.put(splitted[0], splitted[1]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserMainPage login() {
        driver.get(baseUrl);
        driver.findElement(By.xpath(loginLocator)).sendKeys(userEmail);
        driver.findElement(By.xpath(passwordLocator)).sendKeys(userPassword);
        driver.findElement(By.xpath(submitLocator)).click();
        return new UserMainPage(driver);
    }

    @Override
    void check(WebDriver driver) {
    }
}
