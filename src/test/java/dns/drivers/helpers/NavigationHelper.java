package dns.drivers.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    // Логгер
    private static Logger logger = LogManager.getLogger(NavigationHelper.class);

    // Веб драйвер
    private static WebDriver navigation;

    // Инициализация драйвера
    public static void init(WebDriver driver) {
        navigation = driver;
    }

    // Открытие новой страницы
    public static void navigateTo(String URL) {
        navigation.navigate().to(URL);
    }

    // Обновление страницы
    public static void refresh() {
        navigation.navigate().refresh();
    }
}
