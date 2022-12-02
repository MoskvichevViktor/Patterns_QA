package dns.tests.dns.matchers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import dns.pages.KitchenAppliancesPage;

public class KitchenAppliancesPageMatcher {

    KitchenAppliancesPage page;

    private Logger logger = LogManager.getLogger(KitchenAppliancesPageMatcher.class);

    // Конструктор
    public KitchenAppliancesPageMatcher(KitchenAppliancesPage page) {
        this.page = page;
    }

    // Проверка
    public void checkPageTextEquals(String expected, String actual) {
        Assertions.assertEquals(expected, actual, "Текст Техника для кухни не отображается.");
        logger.info("Проверка на отображения текста Техника для кухни - пройдена!");
    }

    // Проверка
    public void checkPageLinkEquals(String expected, String actual) {
        Assertions.assertEquals(expected, actual, "Ссылка Собрать свою кухню не отображается.");
        logger.info("Проверка на отображения ссылки Собрать свою кухню - пройдена!");
    }

    public void checkCountSubcategory(Integer expected, Integer actual) {
        Assertions.assertTrue( expected < actual, "Количество категорий меньше или равно пяти");
        logger.info("Проверка на количество категорий раздела Техника для кухни  - пройдена!");
    }
}
