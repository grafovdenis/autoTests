import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LocatorsTest {
    private WebDriver driver;
    private String baseUrl;
    private JavascriptExecutor jsx;
    private Map<String, String> user;

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

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://ok.ru";
        jsx = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        user = readConfig();
    }

    @Test
    public void testCase() throws Exception {
        String userEmail = user.get("email");
        String userPassword = user.get("password");

        String loginLocator = "//input[@name='st.email']";
        String passwordLocator = "//input[@name='st.password']";
        String submitLocator = "//input[@data-l='t,sign_in']";

        String friendsLocator = "//a[@data-l='t,userFriend']";

        String friendsCardsLocator = "//div[@class='user-grid-card']";
        String friendNamesLocator = "//a[@class='n-t bold']";


        driver.get(baseUrl);
        driver.findElement(By.xpath(loginLocator)).sendKeys(userEmail);
        driver.findElement(By.xpath(passwordLocator)).sendKeys(userPassword);
        driver.findElement(By.xpath(submitLocator)).click();

        driver.findElement(By.xpath(friendsLocator)).click();

        WebElement firstFriend = driver.findElement(By.xpath(friendNamesLocator));
        System.out.println("First friend card locator: " + friendsCardsLocator);
        System.out.println("First friend name: " + firstFriend.getText());
        String firstFriendId = firstFriend.getAttribute("href").replaceAll("https://ok.ru/profile/", "");
        System.out.println("First friend id: " + firstFriendId);

        String typeToFirstFriendLocator = String.format("//a[@data-l='t,sendMessage' and contains(@href,'%s') and contains(@class, '__wide')]", firstFriendId);
        System.out.println("Type to first friend locator: " + typeToFirstFriendLocator);

        driver.findElement(By.xpath(typeToFirstFriendLocator)).click();

        String openedChatTitle = driver.findElement(By.xpath("//span[@data-l='t,menu_opponent_name']")).getText();
        System.out.println("Opened chat title: " + openedChatTitle);
        String chatAtLeftColumnLocator = String.format("//div[@data-id='PRIVATE_%s' and @data-l='t,item']", firstFriendId);
        System.out.println("Chat at left column locator: " + chatAtLeftColumnLocator);
    }
}
