package dns.tests.dns.matchers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import dns.pages.ElektrikStovePage;

public class ElektrikStovePageMatcher {

    ElektrikStovePage page;

    private Logger logger = LogManager.getLogger(ElektrikStovePageMatcher.class);

    // Конструктор
    public ElektrikStovePageMatcher(ElektrikStovePage page) {
        this.page = page;
    }

    // Проводим проверку на соответствие на количества товара
    public void checkCountProduct(Integer expected, Integer actual) {
        Assertions.assertTrue(actual > expected, "Количество товара в разделе Плиты электрические меньше или равно 100.");
        logger.info("Проверка на количество товара в разделе Плиты электрические - пройдена!");
    }
}
