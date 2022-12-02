package dns.tests;

import dns.drivers.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class BaseTest {
    // Драйвер браузера
    protected static WebDriver driver;
    // Логгер
    private Logger logger = LogManager.getLogger(BaseTest.class);

    // Чтение передаваемого параметра browser (-Dbrowser)
    String env = System.getProperty("browser", "chrome");

    // Чтение передаваемого параметра pageLoadStrategy (-DpageLoadStrategy)
    String pls = System.getProperty("pageLoadStrategy", "normal");


    // Перед каждым тестом
    @BeforeEach
    public void setUp() throws Exception {
        logger.info("env = " + env);
        logger.info("pls = " + pls);
        driver = WebDriverFactory.getDriver(env.toLowerCase(), pls.toLowerCase(Locale.ROOT));
        logger.info("Драйвер стартовал!");
    }

    // После каждого теста
    @AfterEach
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }
}