package ru.yandex.praktikum.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private By nameField = By.xpath(".//label[text() = 'Имя']/parent::div/input");
    private By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    private By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private By incorrectPasswordError = By.xpath(".//*[@class = 'input__error text_type_main-default']");
    private By loginButton = By.xpath(".//*[text() = 'Войти']");

    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Enter name")
    public void enterName(String name) {
        webDriver.findElement(nameField).sendKeys(name);
    }

    @Step("Enter email")
    public void enterEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click the register button in the register page")
    public void clickRegisterButton() {
        webDriver.findElement(registerButton).click();
    }

    @Step("Click the login button in the register page")
    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }

    public void fillTheFieldsRegisterPage(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Get text incorrect password")
    public String getTextIncorrectPassword() {
        return webDriver.findElement(incorrectPasswordError).getText();
    }
}
