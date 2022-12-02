package dns.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import dns.elements.Button;
import dns.elements.Link;
import dns.drivers.helpers.WaitHelper;

import java.util.ArrayList;
import java.util.List;

public class StartPage extends BasePage{
    private final String URL = "https://www.dns-shop.ru/";

    private Logger logger = LogManager.getLogger(AppliancesPage.class);

    public StartPage(WebDriver driver) {
        super(driver);
        // Инициализация веб элементов
        PageFactory.initElements(driver, this);
    }

    // Веб элемент кнопка Всё верно
    @FindBy(xpath = "//span[text()=\"Всё верно\"]/parent::button" )
    private WebElement buttonAssept;
    // Веб элемент ссылка Бытовая техника
    @FindBy(xpath = "//a[text()='Бытовая техника']")
    private WebElement linkAppliances;
    // Список веб элементов раздела Бытовая техника
    @FindBy(xpath = "//a[@class ='ui-link menu-desktop__first-level']")
    private List<WebElement> listTechniqueCategories;
    // Веб элемент ссылка Холодильное оборудование
    @FindBy(xpath = "//*[text() ='Холодильное оборудование']")
    private WebElement linkRefrigerationEquipment;
    // Cписок веб элементов из раздела Приготовление пищи
    @FindBy(xpath = "//*[text() ='Холодильное оборудование']//a")
    private List<WebElement> listRefrigerationEquipment;
    // Веб элемент Плиты (stove)
    @FindBy(xpath = "//*[text()='Плиты и печи']")
    private WebElement linkStove;
    // Веб элемент Плиты электрические
    @FindBy(xpath = "//span[text()='Плиты электрические']")
    private WebElement linkElektrikStove;
    // Веб элемент Ссылка "ПК, ноутбуки, периферия"
    @FindBy(xpath = "//a[text()='ПК, ноутбуки, периферия']")
    private WebElement linkComputerAndLaptop;
    // Веб элемент Ссылка ноутбуки
    @FindBy(xpath = "//*[text() ='Ноутбуки']")
    private WebElement linkLaptop;

    // Открытие страницы
    public void openPage() {
        driver.get(this.URL);
        logger.info("Открыта страница https://www.dns-shop.ru/");
    }

    // Убираем всплывающее окно
    public Button clickButtonAssept() {
        //WaitHelper.clickabilityOfElement(buttonAssept);
        return new Button(buttonAssept);
    }

    public Link getLinkAppliances(){
        return new Link(linkAppliances);
    }

    public List<String> getAppliancesCategoryName() throws InterruptedException {
        List<String> listTechniqueCategoriesNames = new ArrayList<>();
        WaitHelper.presenceOfAllElementsLocatedBy(By.xpath("//a[@class ='ui-link menu-desktop__first-level']"));
        for (WebElement element : listTechniqueCategories) {
            logger.info("Бытовая техника - " + element.getText());
            listTechniqueCategoriesNames.add(element.getText());
        }
        return listTechniqueCategoriesNames;
    }

    public Link getLinkRefrigerationEquipment(){
        return new Link(linkRefrigerationEquipment);
    }

    public List<String> getListRefrigerationEquipmentName() throws InterruptedException {
        List<String> listRefrigerationEquipmentName = new ArrayList<>();
        WaitHelper.presenceOfAllElementsLocatedBy(By.xpath("//*[text() ='Холодильное оборудование']//a"));
        for (WebElement element : listRefrigerationEquipment) {
            listRefrigerationEquipmentName.add(element.getText());
        }
        return listRefrigerationEquipmentName;
    }

    // Ссылка на Плиты
    public Link getLinkStove(){
        return new Link(linkStove);
    }

    // Ссылка на Электрические плиты
    public Link getLinkElektrikStove(){
        return new Link(linkElektrikStove);
    }

    // Ссылка ПК, ноутбуки, периферия
    public Link getLinkComputerAndLaptop() {
        return new Link(linkComputerAndLaptop);
    }
    // Ссылка Ноутбуки
    public Link getLinkLaptop() {
        return new Link(linkLaptop);
    }
}
