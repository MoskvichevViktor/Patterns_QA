package dns.elements;

import org.openqa.selenium.WebElement;
import dns.drivers.helpers.ActionHelper;
import dns.drivers.helpers.WaitHelper;

// Класс "Гармошка"
public class Accordeon extends BaseElement {

    // Конструктор
    public Accordeon(WebElement webElement) {
        super(webElement);
    }

    // Раскрытие гармошки
    public void show() {
        // Ожидание кликабельности гармошки
        WaitHelper.clickabilityOfElement(webElement);
        ActionHelper.moveToElement(webElement);
        webElement.click();
    }
}
