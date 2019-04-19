package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FriendsPage extends BasePage {
    private String friendCardsLocator = ".//*[@class='user-grid-card']";
    private String friendNamesLocator = "//a[@class='n-t bold']";

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    public List<CardWrapper> findAllFriendCards() {
        List<WebElement> elements = driver.findElements(By.xpath(friendCardsLocator));
        return CardTransformer.wrap(elements);
    }

    public void getFriendCardByName(String name) {
        String locator = String.format(".//div[@class='user-grid-card' and .//a[contains(text(), '%s')]]", name);
        driver.findElement(By.xpath(locator));
    }

    @Override
    void check(WebDriver driver) {
    }
}
