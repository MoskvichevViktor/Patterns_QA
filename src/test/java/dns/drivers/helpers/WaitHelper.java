package dns.drivers.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Класс ожиданий событий на странице
public class WaitHelper {
    // Логгер
    private static Logger logger = LogManager.getLogger(WaitHelper.class);
    // Ожидание драйвера браузера
    protected static WebDriverWait webDriverWait;

    // Инициализация ожидания драйвера браузера
    // Установка таймаута ожидания и интервала опроса
    public static void init(WebDriver driver, Duration timeOut, Duration sleep) {
        webDriverWait = new WebDriverWait(driver, timeOut, sleep);
    }

    //
    public static void presenceOfAllElementsLocatedBy(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // Ожидание кликабельности элемента
    public static void clickabilityOfElement(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    // Ожидание видимости элемента
    public static void visibilityOfElement(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    // Ожидание исчезновения header со страницы
    public static void invisibilityOf(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));
    }
}
