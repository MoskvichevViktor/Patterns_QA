package dns.tests.dns;

import dns.drivers.helpers.NavigationHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import dns.pages.ElektrikStovePage;
import dns.pages.StartPage;
import dns.tests.BaseTest;
import dns.tests.dns.matchers.ElektrikStovePageMatcher;
import dns.tests.dns.matchers.StartPageMatcher;

import java.util.Arrays;
import java.util.List;

public class HW01Case02Test extends BaseTest {

    // Список в который положим разделы Бытовой техники
    List<String> actualListCategory;
    // Переменная в которую положим количество категорий раздела Холодильное оборудование
    int actualCountSubCategory;
    // Переменная в которую положим количество товара
    int actualCountProduct;

    private Logger logger = LogManager.getLogger(HW01Case02Test.class);

    @Test
    public void secondCase() throws InterruptedException {
        // 1. Arrange

        // 2. Act
        StartPage startPage = getStartPage();
        ElektrikStovePage elektrikStovePage = getElektrikStovePage();

        // 3. Assert

        // Проверяемые параметры
        // Список категорий раздела Бытовая техника
        List<String> expectedListCategory = Arrays.asList("Встраиваемая техника","Техника для кухни", "Техника для дома");
        // Количество категорий из раздела Холодильное оборудование больше пяти
        int expectedCountSubCategory = 5;
        // Количества товара электрические плиты больше чем сто
        int expectedCountProduct = 100;

        StartPageMatcher startPageMatcher = new StartPageMatcher(startPage);
        ElektrikStovePageMatcher elektrikStovePageMatcher = new ElektrikStovePageMatcher(elektrikStovePage);

        // Проверка разделов бытовой техники на соответствие заданному списку
        startPageMatcher.checkPageListCategoryEquals(expectedListCategory, actualListCategory);
        // Проверяем что количество категорий из раздела Холодильное оборудование больше пяти
        startPageMatcher.checkCountSubcategory(expectedCountSubCategory, actualCountSubCategory);
        // Проводим проверку на соответствие количества товара Электрические плиты больше чем сто
        elektrikStovePageMatcher.checkCountProduct(expectedCountProduct, actualCountProduct);
    }

    public StartPage getStartPage() throws InterruptedException {
        //  Стартовая страница сайта DNS
        StartPage startPage = new StartPage(driver);
        // Открыть страницу https://www.dns-shop.ru/
        startPage.openPage();
        // Закрытие всплывающего окна
        startPage.clickButtonAssept().click();
        // Обновляем страницу
        NavigationHelper.refresh();
        // Наводим курсор по ссылке Бытовая техника
        startPage.getLinkAppliances().focusOnLink();
        // Получаем список категорий
        actualListCategory = startPage.getAppliancesCategoryName();
        // Наводим курсор на ссылку Холодильное оборудование
        startPage.getLinkRefrigerationEquipment().focusOnLink();
        // Получаем список веб элементов из раздела Холодильное оборудование и пишем его в логи
        List<String> list = startPage.getListRefrigerationEquipmentName();
        for (String x : list) {
            logger.info("Холодильное оборудование - " + x);
        }
        actualCountSubCategory = startPage.getListRefrigerationEquipmentName().size();
        // Наводим курсор на плиты (stove) и кликаем
        startPage.getLinkStove().focusOnLink();
        startPage.getLinkStove().click();

        return new StartPage(driver);
    }

    public ElektrikStovePage getElektrikStovePage() {

        StartPage startPage = new StartPage(driver);
        // Переходим по ссылке Плиты электрические
        startPage.getLinkElektrikStove().click();

        ElektrikStovePage elektrikStovePage = new ElektrikStovePage(driver);
        // Заносим в переменную количество найденного товара
        actualCountProduct = elektrikStovePage.getTextProductsCount();

        return new ElektrikStovePage(driver);
    }
}
