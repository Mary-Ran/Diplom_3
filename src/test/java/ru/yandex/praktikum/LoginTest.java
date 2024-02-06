package ru.yandex.praktikum;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.dto.UserCreateRequest;
import ru.yandex.praktikum.po.*;
import ru.yandex.praktikum.steps.UserSteps;

public class LoginTest {
    private WebDriver webDriver;
    private WebDriverFactory webDriverFactory;
    private String name;
    private String email;
    private String password;
    private String accessToken;
    private UserSteps userSteps;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ElementsToolbar elementsToolbar;
    private RegisterPage registerPage;
    private RestorePasswordPage passwordRecoveryPage;

    @Before
    public void setup() {
        webDriverFactory = new WebDriverFactory();
        webDriver = webDriverFactory.getWebDriver();
        name = RandomStringUtils.randomAlphanumeric(10);
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(10);
        userSteps = new UserSteps();
        accessToken = userSteps.userCreate(new UserCreateRequest(email, password, name))
                .then()
                .extract().body().path("accessToken");
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        elementsToolbar = new ElementsToolbar(webDriver);
        registerPage = new RegisterPage(webDriver);
        passwordRecoveryPage = new RestorePasswordPage(webDriver);
        userSteps.openBaseUri(webDriver);
    }

    @Test
    @Description("Проверка входа через кнопку 'Войти в аккаунт' на главной странице")
    public void checkSuccessfulLoginUsingTheLoginToAccountButton() {
        mainPage.waitWhenLoginToAccountButtonToBeClickable();
        mainPage.clickLoginToAccountButton();
        loginPage.fillTheFieldsLoginPage(email, password);
        loginPage.clickLoginButton();
        mainPage.waitWhenTheOrderCreateButtonIsVisible();

        Assert.assertTrue(mainPage.checkThatTheOrderCreateButtonIsDisplayed());
    }

    @Test
    @Description("Проверка входа через кнопку 'Личный кабинет' на главной странице")
    public void checkSuccessfulLoginUsingThePersonalAccountButton() {
        elementsToolbar.waitWhenPersonalAccountButtonToBeClickable();
        elementsToolbar.clickPersonalAccountButton();
        loginPage.fillTheFieldsLoginPage(email, password);
        loginPage.clickLoginButton();
        mainPage.waitWhenTheOrderCreateButtonIsVisible();

        Assert.assertTrue(mainPage.checkThatTheOrderCreateButtonIsDisplayed());
    }

    @Test
    @Description("Проверка входа через кнопку 'Войти' на странице регистрации")
    public void checkSuccessfulLoginUsingTheLoginButtonInRegisterPage() {
        elementsToolbar.waitWhenPersonalAccountButtonToBeClickable();
        elementsToolbar.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickLoginButton();
        loginPage.fillTheFieldsLoginPage(email, password);
        loginPage.clickLoginButton();
        mainPage.waitWhenTheOrderCreateButtonIsVisible();

        Assert.assertTrue(mainPage.checkThatTheOrderCreateButtonIsDisplayed());
    }

    @Test
    @Description("Проверка входа через кнопку 'Войти' на странице восстановления пароля")
    public void checkSuccessfulLoginUsingTheRestorePasswordButtonInRestorePasswordPage() {
        elementsToolbar.waitWhenPersonalAccountButtonToBeClickable();
        elementsToolbar.clickPersonalAccountButton();
        loginPage.clickRestorePasswordButton();
        passwordRecoveryPage.clickLoginButton();
        loginPage.fillTheFieldsLoginPage(email, password);
        loginPage.clickLoginButton();
        mainPage.waitWhenTheOrderCreateButtonIsVisible();

        Assert.assertTrue(mainPage.checkThatTheOrderCreateButtonIsDisplayed());
    }

    @After
    public void tearDown() {
        webDriver.quit();
        userSteps.userDelete(accessToken);
    }
}
