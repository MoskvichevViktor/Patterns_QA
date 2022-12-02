package dns.tests.dns;

import dns.drivers.helpers.NavigationHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import dns.pages.AppliancesPage;
import dns.pages.KitchenAppliancesPage;
import dns.pages.StartPage;
import dns.tests.BaseTest;
import dns.tests.dns.matchers.AppliancesPageMatcher;
import dns.tests.dns.matchers.KitchenAppliancesPageMatcher;

public class HW01Case01Test extends BaseTest {

    private Logger logger = LogManager.getLogger(HW01Case01Test.class);

    // Переменные в которые положим проверяемый текст со страницы
    String actualCategoryName;
    // Переменные в которые положим проверяемый текст со страницы
    String actualSubCategoryName;
    // Переменная в которую положим проверяемую ссылку (проверка ссылки Собрать свою кухню)
    String actualLink;
    // Переменная в которую положим количество категорий раздела Техника для кухни
    int actualCountSubCategory;

    @Test
    public void firstCase() {

        // 1. Arrange

        // 2. Act
        AppliancesPage appliancesPage = getAppliancesPage();
        KitchenAppliancesPage kitchenAppliancesPage = getKitchenAppliancesPage();

        // 3. Assert

        // Проверяемые параметры
        String expectedCategoryName = "Бытовая техника";
        String expectedSubCategoryName  = "Техника для кухни";
        String expectedLink  = "Собрать свою кухню";
        int expectedCountSubCategory = 5;

        AppliancesPageMatcher appliancesPageMatcher = new AppliancesPageMatcher(appliancesPage);
        KitchenAppliancesPageMatcher kitchenAppliancesPageMatcher = new KitchenAppliancesPageMatcher(kitchenAppliancesPage);

        // Проверка отображения текста Бытовая техника
        appliancesPageMatcher.checkPageTextEquals(expectedCategoryName, actualCategoryName);
        // Проверка на отображение текста Техника для кухни
        kitchenAppliancesPageMatcher.checkPageTextEquals(expectedSubCategoryName, actualSubCategoryName);
        // Проверка на наличие ссылки Собрать свою кухню
        kitchenAppliancesPageMatcher.checkPageLinkEquals(expectedLink, actualLink);
        // Проверка условия на количество субкатегорий > 5
        kitchenAppliancesPageMatcher.checkCountSubcategory(expectedCountSubCategory, actualCountSubCategory);

    }

    public AppliancesPage getAppliancesPage() {
        // Стартовая страница сайта DNS
        StartPage startPage = new StartPage(driver);
        // Открыть страницу https://www.dns-shop.ru/
        startPage.openPage();
        // Выводим в логи заголовок
        logger.info("Заголовок страницы - " + driver.getTitle());
        // Выводим в логи url
        logger.info("Текущий URL - " + driver.getCurrentUrl());
        // Выводим в логи размер окна браузера
        logger.info("Размеры окна браузера - " + driver.manage().window().getSize());
        // Закрытие всплывающего окна
        startPage.clickButtonAssept().click();
        // Обновляем страницу
        NavigationHelper.refresh();
        // Переходим по ссылке Бытовая техника
        startPage.getLinkAppliances().click();

        AppliancesPage appliancesPage = new AppliancesPage(driver);

        // Заносим в переменную текущее отображение текста на странице для дальнейшей проверки
        actualCategoryName = appliancesPage.getTextBoxTechnique();

        return new AppliancesPage(driver);
    }

    public KitchenAppliancesPage getKitchenAppliancesPage() {

        AppliancesPage appliancesPage = new AppliancesPage(driver);
        // Переходим по ссылке Техника для кухни (kitchenAppliances)
        appliancesPage.getLinkKitchenAppliances().click();

        KitchenAppliancesPage kitchenAppliancesPage = new KitchenAppliancesPage(driver);

        // Выводим в логи названия всех категорий раздела Техника для кухни
        kitchenAppliancesPage.getSubCategoryName();

        // Заносим в переменную текущее отображение текста на странице для дальнейшей проверки
        actualSubCategoryName = kitchenAppliancesPage.getTextBoxKitchenAppliances();

        // Заносим в переменную проверяемую ссылку (проверка ссылки Собрать свою кухню)
        actualLink = kitchenAppliancesPage.getLinkMakeKitchen().getText();

        // Заносим в переменную количество категорий раздела Техника для кухни
        actualCountSubCategory = kitchenAppliancesPage.getCountSubCategory();

        return new KitchenAppliancesPage(driver);
    }
}
