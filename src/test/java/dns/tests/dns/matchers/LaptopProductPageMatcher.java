package dns.tests.dns.matchers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import dns.pages.LaptopProductPage;

public class LaptopProductPageMatcher {

    LaptopProductPage page;

    private Logger logger = LogManager.getLogger(LaptopProductPageMatcher.class);

    // Конструктор
    public LaptopProductPageMatcher(LaptopProductPage page) {
        this.page = page;
    }

    // Проверка
    public void checkPageTitleEquals(String expected) {
        String actual = page.getPageTitle();
        Assertions.assertTrue(actual.contains(expected), "Заголовок страницы НЕ соответствует ожидаемому.");
        logger.info("Заголовок страницы соответствует ожидаемому.");
    }

    // Проверить, что в блоке Характеристики заголовок содержит ASUS
    public void checkNameEquals(String expected, String actual) {
        Assertions.assertTrue(actual.contains(expected), "В блоке Характеристики заголовок НЕ содержит ASUS.");
        logger.info("Заголовок блока характеристик соответствует ожидаемому.");
    }

    // Проверка, что в блоке Характеристики значение Объем оперативной памяти равно 32 ГБ
    public void checkRam(String expected, String actual) {
        Assertions.assertTrue(actual.contains(expected), "В блоке Характеристики значение Объем оперативной памяти НЕ равно 32 ГБ.");
        logger.info("В блоке Характеристики значение Объем оперативной памяти равно 32 ГБ.");
    }
}
