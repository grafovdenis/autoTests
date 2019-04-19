package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardWrapper {
    private WebElement element;
    private By writeSelector;
    private static final By NAME = By.className("n-t");

    public CardWrapper(WebElement element) {
        this.element = element;
    }

    public String getName() {
        System.out.println(element.findElement(NAME).getText());
        return element.findElement(NAME).getText();
    }

    public void writeMessage() {
        element.findElement(writeSelector).click();
    }

//    public String getName() {
//        return name;

//    }
}
