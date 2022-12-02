package dns.tests.dns;

import dns.drivers.helpers.NavigationHelper;
import dns.listener.Listener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import dns.drivers.helpers.JavaScriptHelper;
import dns.drivers.helpers.WaitHelper;
import dns.pages.LaptopPage;
import dns.pages.LaptopProductPage;
import dns.pages.StartPage;
import dns.tests.BaseTest;
import dns.tests.dns.matchers.LaptopProductPageMatcher;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class HW02Case03Test extends BaseTest {

    // Переменная в которую положим название первого элемента после сортировки для дальнейшей проверки
    String expectedNameFirstElement;
    String type = "Сначала дорогие";
    // Счетчик для нумерации скринов
    private int count = 1;

    private Logger logger = LogManager.getLogger(HW02Case03Test.class);

    @Test
    public void thirdCase() {

        // 1. Arrange

        // Производитель
        String company = "ASUS";
        // Объем оперативной памяти
        String ram = "32 ГБ";

        // 2. Act
        LaptopProductPage laptopProductPage = getProductPage(company, ram);

        // 3. Assert

        LaptopProductPageMatcher laptopProductPageMatcher = new LaptopProductPageMatcher(laptopProductPage);
        // Проверка заголовка открытой по ссылке первого элемента страницы
        laptopProductPageMatcher.checkPageTitleEquals(expectedNameFirstElement);
        // Проверить, что в блоке Характеристики заголовок содержит ASUS
        laptopProductPageMatcher.checkNameEquals(company, laptopProductPage.getActualNameCompany());
        // Проверка, что в блоке Характеристики значение Объем оперативной памяти равно 32 ГБ
        laptopProductPageMatcher.checkRam(ram, laptopProductPage.getRam());

    }

    public LaptopProductPage getProductPage(String company, String ram) {
        // Стартовая страница сайта DNS
        StartPage startPage = new StartPage(driver);
        // Открыть страницу https://www.dns-shop.ru/
        startPage.openPage();
        // Регистрация слушателя событий
        Listener listener = new Listener();
        WebDriver eventFiringWebDriver = new EventFiringDecorator<>(listener).decorate(driver);
        // Скриншот всей страницы (с прокруткой) после загрузки страницы
        makeScreenshotFullPage();
        // Закрытие всплывающего окна
        startPage.clickButtonAssept().click();
        // Обновляем страницу
        NavigationHelper.refresh();
        // Наводим курсор на ссылку ПК, ноутбуки, периферия
        startPage.getLinkComputerAndLaptop().focusOnLink();
        // Скриншот всей страницы (с прокруткой) после открытия меню
        makeScreenshotFullPage();
        // Переходим по ссылке Ноутбуки
        startPage.getLinkLaptop().click();

        // Страница Ноутбуки
        LaptopPage laptopPage = new LaptopPage(eventFiringWebDriver);
        // Скрываем блок страницы
        laptopPage.blockHeaderHide();
        // Скриншот всей страницы (с прокруткой) после скрытия блока
        WaitHelper.invisibilityOf(laptopPage.getHeader());
        makeScreenshotFullPage();
        JavaScriptHelper.scrollBy(0, 800);
        // Установка фильтра "Производитель"
        laptopPage.checkboxCompany(company);
        JavaScriptHelper.scrollBy(0, 500);
        // Отображение фильтра "Объем оперативной памяти"
        laptopPage.accordeonRAM().show();
        // Установка фильтра "Объем оперативной памяти"
        laptopPage.checkboxRAM(ram).setChecked(true);
        JavaScriptHelper.scrollBy(0, 500);
        // Нажатие на кнопку "Применить"
        laptopPage.buttonApply().click();
        // Скриншот всей страницы (с прокруткой) после применения фильтров
        makeScreenshotFullPage();
        // Отображение сортировки
        laptopPage.accordeonSort().show();
        // Установка сортировки "Сначала дорогие"
        laptopPage.radiobuttonSort(type).setSelected(true);
        // Обновляем страницу для построения списка согласно требованию - Сначала дорогие
        NavigationHelper.refresh();
        // Скриншот всей страницы (с прокруткой) после применения сортировки
        makeScreenshotFullPage();

        // Заносим название ноутбука в переменную для дальнейшей проверки
        expectedNameFirstElement = laptopPage.getNameFirstElement();

        // Нажатие на ссылку первого продукта в списке
        laptopPage.linkFirstProduct().openInNewWindow();
        // Скриншот всей страницы (с прокруткой) после загрузки новой страницы
        makeScreenshotFullPage();

        // Страница по ссылке первого элемента
        LaptopProductPage laptopProductPage = new LaptopProductPage(eventFiringWebDriver);
        JavaScriptHelper.scrollBy(0, 500);
        // Открываем все характеристики
        laptopProductPage.buttonAllCharacteristics().click();

        return new LaptopProductPage(eventFiringWebDriver);
    }

    // Метод скриншотирования всей веб страницы.
    private void makeScreenshotFullPage() {
        try {
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "png", new File("temp\\ScreenshotFromCase"+count+".png"));
            logger.info("Скриншот сохранен.");
            count++;
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.head.scrollHeight)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
