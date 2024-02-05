package ru.yandex.praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    public WebDriver getWebDriver() {
        switch (System.getProperty("browser")) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/bin/yandexdriver.exe");
                return new ChromeDriver();
            default:
                return new ChromeDriver();
        }
    }
}
