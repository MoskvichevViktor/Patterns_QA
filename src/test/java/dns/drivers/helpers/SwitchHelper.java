package dns.drivers.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class SwitchHelper {

    // Логгер
    private static Logger logger = LogManager.getLogger(SwitchHelper.class);

    // Веб драйвер
    private static WebDriver switcher;

    // Инициализация драйвера
    public static void init(WebDriver driver) {
        switcher = driver;
    }

    // Переключение на создаваемое окно
    public static void switchToNewWindow() {
        switcher.switchTo().newWindow(WindowType.WINDOW);
    }
}
