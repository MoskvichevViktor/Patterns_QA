package dns.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import dns.elements.*;
import dns.drivers.helpers.JavaScriptHelper;
import dns.drivers.helpers.WaitHelper;

import java.util.List;

public class LaptopPage extends BasePage {

    private Logger logger = LogManager.getLogger(LaptopPage.class);

    private String company = "ASUS";

    public LaptopPage(WebDriver driver) {
        super(driver);
        // Инициализация веб элементов
        PageFactory.initElements(driver, this);
    }


    // Веб элемент header
    @FindBy(xpath = "//header")
    private WebElement blockHeader;
    // Веб элемент Строка поиска
    @FindBy(xpath = "//input[@placeholder='Поиск']")
    private WebElement inputCompany;
    // Веб элемент Чекбокс Асус
    @FindBy(xpath = "//span[text()='ASUS  ']")
    private WebElement checkBoxCompany;
    // Веб элемент Аккордеон Объем оперативной памяти (ГБ)
    @FindBy(xpath = "//span[text()='Объем оперативной памяти (ГБ)']")
    private WebElement accordeonRAM;
    // Веб элемент Чекбокс 32 ГБ
    @FindBy(xpath = "//span[text()='32 ГБ  ']")
    private List<WebElement> checkboxRAM;
    // Веб элемент кнопка применить
    @FindBy(xpath = "//button[text()='Применить']")
    private WebElement buttonApply;
    // Веб элемент Сортировка
    @FindBy(xpath = "//span[text()='Сортировка:']")
    private WebElement accordeonSort;
    // Веб элемент радио кнопка 'Сначала дорогие'
    @FindBy(xpath = "//*[text()='Сначала дорогие']")
    private List<WebElement> radiobuttonSort;
    // Веб элемент Аккордеон Производитель
    @FindBy(xpath = "//span[text()='Производитель']")
    private WebElement accordeonCompany;
    // Веб элемент чекбокс Производитель
    @FindBy(xpath = "//span[text()=\"Производитель\"]/../../div//label/span[1]")
    private List<WebElement> checkboxCompany;
    // Веб элемент ссылка на первый элемент
    @FindBy(xpath = "(//a[contains(@class, \"catalog-product__name\")])[1]")
    private WebElement linkFirstProduct;
    // Веб элемент название первого найденного элемента
    @FindBy(xpath = "//div[@data-id='product']/a")
    private WebElement textFirstProduct;

    // Скрытие шапки
    public void blockHeaderHide() {
        JavaScriptHelper.displayNone(blockHeader);
    }

    // Чекбоксы "Производитель"
    public CheckBox checkboxCompany(String company) {

        WaitHelper.visibilityOfElement(inputCompany);
        inputCompany.click();
        inputCompany.sendKeys(company);
        WaitHelper.clickabilityOfElement(checkBoxCompany);
        checkBoxCompany.click();
        return null;
    }

    // Аккордеон "Объем оперативной памяти"
    public Accordeon accordeonRAM() {
        return new Accordeon(accordeonRAM);
    }
    // Чекбоксы "Объем оперативной памяти"
    public CheckBox checkboxRAM(String ram) {
        for (WebElement webElement : checkboxRAM)
            if (webElement.getText().contains(ram)) {
                return new CheckBox(webElement);
            }
        return null;
    }

    // Кнопка "Применить"
    public Button buttonApply() {
        return new Button(buttonApply);
    }

    // Аккордеон "Сортировка"
    public Accordeon accordeonSort() {
        return new Accordeon(accordeonSort);
    }

    // Переключатели "Сортировка"
    public RadioButton radiobuttonSort(String type) {
        for (WebElement webElement : radiobuttonSort) {
            if(webElement.getText().contains(type)) {
                return new RadioButton(webElement);
            }
        }
        return null;
    }

    // Ссылка на первый продукт в списке
    public Link linkFirstProduct() {
        return new Link(linkFirstProduct);
    }

    //Получаем название ноутбука для дальнейшей проверки на соответствие ожидаемому заголовку страницы
    public String getNameFirstElement(){
        WaitHelper.visibilityOfElement(textFirstProduct);
        String expectedTitle = textFirstProduct.getText();
        expectedTitle = expectedTitle.substring(0, expectedTitle.indexOf("["));
        return expectedTitle;
    }

    // Получаем веб элемент header
    public WebElement getHeader() {
        return blockHeader;
    }
}
