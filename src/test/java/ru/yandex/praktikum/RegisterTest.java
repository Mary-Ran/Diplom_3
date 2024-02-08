package ru.yandex.praktikum;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.dto.UserLoginRequest;
import ru.yandex.praktikum.po.*;
import ru.yandex.praktikum.steps.UserSteps;
import ru.yandex.praktikum.steps.VerificationSteps;

public class RegisterTest {
    private WebDriver webDriver;
    private WebDriverFactory webDriverFactory;
    private String name;
    private String email;
    private String password;
    private String accessToken;
    private UserSteps userSteps;
    private VerificationSteps verificationSteps;
    private final String expectedErrorText = "Некорректный пароль";
    private LoginPage loginPage;
    private ElementsToolbar elementsToolbar;
    private RegisterPage registerPage;
    

    @Before
    public void setup() {
        webDriverFactory = new WebDriverFactory();
        webDriver= webDriverFactory.getWebDriver();
        name = RandomStringUtils.randomAlphanumeric(10);
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        userSteps = new UserSteps();
        verificationSteps = new VerificationSteps();
        elementsToolbar = new ElementsToolbar(webDriver);
        loginPage = new LoginPage(webDriver);
        registerPage = new RegisterPage(webDriver);
        userSteps.openBaseUri(webDriver);
    }

    @Test
    @Description("Проверка успешной регистрации")
    public void checkSuccessfulRegister() {
        password = RandomStringUtils.randomAlphanumeric(10);
        elementsToolbar.waitWhenPersonalAccountButtonToBeClickable();
        elementsToolbar.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        registerPage.fillTheFieldsRegisterPage(name, email, password);
        registerPage.clickRegisterButton();

        loginPage.waitWhenTheLoginTitleIsVisible();
        loginPage.checkThatTheLoginTitleIsDisplayed();
    }

    @Test
    @Description("Проверка регистрации с некорректным паролем")
    public void checkEnterAFiveCharactersInPasswordField() {
        password = RandomStringUtils.randomAlphanumeric(5);
        elementsToolbar.waitWhenPersonalAccountButtonToBeClickable();
        elementsToolbar.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        registerPage.fillTheFieldsRegisterPage(name, email, password);
        registerPage.clickRegisterButton();

        String actualErrorText = registerPage.getTextIncorrectPassword();
        verificationSteps.checkThatActualErrorTextEqualExpectedErrorText(expectedErrorText, actualErrorText);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        accessToken = userSteps.userLogin(new UserLoginRequest(email, password))
                .then()
                .extract().body().path("accessToken");
        if (accessToken != null) {
            userSteps.userDelete(accessToken);
        }
    }
}

