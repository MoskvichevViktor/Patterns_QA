package dns.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import dns.elements.Button;

public class LaptopProductPage extends BasePage {

    private Logger logger = LogManager.getLogger(LaptopProductPage.class);

    // Конструктор класса
    public LaptopProductPage(WebDriver driver) {
        // Вызов родительского конструктора
        super(driver);
        // Инициализация веб элементов
        PageFactory.initElements(driver, this);
    }

    // Веб элемент кнопка Развернуть все
    @FindBy(xpath = "//button[text() = 'Развернуть все']")
    private WebElement buttonAllCharacteristics;

    // Веб элемент содержащий название ноутбука
    @FindBy(xpath = "//div[@class='product-card-description__title']")
    private WebElement blockCharacteristics;
    // Веб элемент содержащий значение Объема оперативной памяти
    @FindBy(xpath = "//div[text() = ' Объем оперативной памяти ']/following-sibling::div")
    private WebElement textBoxRAM;

    public Button buttonAllCharacteristics() {
        return new Button(buttonAllCharacteristics);
    }

    public String getActualNameCompany() {
        return blockCharacteristics.getText();
    }

    public String getRam() {
        return textBoxRAM.getText();
    }
}