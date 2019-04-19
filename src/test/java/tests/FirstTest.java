package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CardWrapper;
import pages.FriendsPage;
import pages.LoginPage;
import pages.UserMainPage;

import java.util.List;

public class FirstTest extends BaseTest {
    @Override
    public void testCase() {
        WebDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        UserMainPage userMainPage = loginPage.login();
        FriendsPage friendsPage = userMainPage.clickToFriends();

        List<CardWrapper> allFriendCards = friendsPage.findAllFriendCards();
        for (CardWrapper friendCard :
                allFriendCards) {
            friendCard.getName();
        }
        allFriendCards.get(0).writeMessage();
    }
}
