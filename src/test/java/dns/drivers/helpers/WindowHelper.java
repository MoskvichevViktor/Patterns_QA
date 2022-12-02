package dns.drivers.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WindowHelper {
    // Логгер
    private static Logger logger = LogManager.getLogger(WindowHelper.class);

    // Веб драйвер
    private static WebDriver window;

    // Инициализация драйвера
    public static void init(WebDriver driver) {
        window = driver;
    }

    // Максимизация размеров окна
    public static void maximizeWindow() {
        window.manage().window().maximize();
    }
}
