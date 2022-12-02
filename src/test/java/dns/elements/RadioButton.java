package dns.elements;

import org.openqa.selenium.WebElement;
import dns.drivers.helpers.WaitHelper;

// Класс "Переключатель"
public class RadioButton extends BaseElement {

    // Конструктор
    public RadioButton(WebElement webElement) {
        super(webElement);
    }

    // Установка переключателя
    public void setSelected(boolean value) {
        if (value != isSelected()) {
            // Ожидание кликабельности переключателя
            WaitHelper.clickabilityOfElement(webElement);
            webElement.click();
        }
    }

    // Проверка, что переключатель установлен
    public boolean isSelected() {
        return webElement.isSelected();
    }
}
