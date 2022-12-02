package dns.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElektrikStovePage extends BasePage{

    private Logger logger = LogManager.getLogger(AppliancesPage.class);

    public ElektrikStovePage(WebDriver driver) {
        super(driver);
        // Инициализация веб элементов
        PageFactory.initElements(driver, this);
    }

    // Веб элемент с текстом - Количество найденного товара
    @FindBy(xpath = "//span[@class='products-count']")
    private WebElement textProductsCount;

    public Integer getTextProductsCount() {
        //получаем строковое значение и из него извлекаем число
        String s = textProductsCount.getText();
        String[] words = s.split("\\s");
        int count = Integer.parseInt(words[0]);
        return count;
    }
}
