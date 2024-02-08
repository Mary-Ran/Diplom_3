package ru.yandex.praktikum.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    private By registerButton = By.xpath(".//*[text() = 'Зарегистрироваться']");
    private By emailField = By.xpath(".//*[text() = 'Email']/parent::div/input");
    private By passwordField = By.xpath(".//*[text() = 'Пароль']/parent::div/input");
    private By loginButton = By.xpath(".//button[text() = 'Войти']");
    private By restorePasswordButton = By.xpath(".//*[text() = 'Восстановить пароль']");
    private By loginTitle = By.xpath(".//*[text() = 'Вход']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Click the register button in the login page")
    public void clickRegisterButton() {
        webDriver.findElement(registerButton).click();
    }

    @Step("Enter email")
    public void enterEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }

    public void fillTheFieldsLoginPage(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Click the login button in the login page")
    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }

    @Step("Click the restore password button in the login page")
    public void clickRestorePasswordButton() {
        webDriver.findElement(restorePasswordButton).click();
    }

    @Step("Wait when the login title is visible")
    public void waitWhenTheLoginTitleIsVisible() {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
    }

    @Step("Check that the login title is displayed")
    public boolean checkThatTheLoginTitleIsDisplayed() {
        return webDriver.findElement(loginTitle).isDisplayed();
    }
}