package dns.tests.dns.matchers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import dns.pages.StartPage;

import java.util.List;

public class StartPageMatcher {

    StartPage page;

    private Logger logger = LogManager.getLogger(StartPageMatcher.class);

    // Конструктор
    public StartPageMatcher(StartPage page) {
        this.page = page;
    }

    // Проверка на соответствие ссылок
    public void checkPageListCategoryEquals(List<String> expected, List<String> actual) {
        Assertions.assertEquals(expected, actual, "Отображаемые ссылки не прошли проверку на заданное соответствие!");
        logger.info("Проверка на отображение ссылок в разделе Бытовая техника прошла!");
    }

    //Проверка количества категорий Холодильное оборудование > 5
    public void checkCountSubcategory(Integer expected, Integer actual) {
        Assertions.assertTrue(actual > expected, "Количество категорий меньше или равно пяти");
        logger.info("Проверка на количество категорий раздела Холодильное оборудование  - пройдена!");
    }
}
