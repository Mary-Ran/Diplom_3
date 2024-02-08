package ru.yandex.praktikum.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage extends BasePage {
    private By loginButton = By.xpath(".//*[text() = 'Войти']");

    public RestorePasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Click the login button in the restore password page")
    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }
}
