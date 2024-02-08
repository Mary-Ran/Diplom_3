package ru.yandex.praktikum.po;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final WebDriver webDriver;

    protected BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
