package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends BasePage {
    private String friendsLocator = "//a[@data-l='t,userFriend']";

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    public FriendsPage clickToFriends() {
        driver.findElement(By.xpath(friendsLocator)).click();
        return new FriendsPage(driver);
    }

    @Override
    void check(WebDriver driver) {
    }
}
