package pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CardTransformer {

    private CardTransformer() {}

    public static List<CardWrapper> wrap(List<WebElement> elements) {
        List<CardWrapper> result = new ArrayList<>();
        for (WebElement element :
                elements) {
            result.add(new CardWrapper(element));
        }
        return result;
    }
}
