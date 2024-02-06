package ru.yandex.praktikum;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.dto.UserCreateRequest;
import ru.yandex.praktikum.po.ElementsToolbar;
import ru.yandex.praktikum.po.LoginPage;
import ru.yandex.praktikum.po.MainPage;
import ru.yandex.praktikum.po.ProfilePage;
import ru.yandex.praktikum.steps.UserSteps;

public class TransitionBetweenPagesTest {
    private WebDriver webDriver;
    private WebDriverFactory webDriverFactory;
    private String name;
    private String email;
    private String password;
    private String accessToken;
    private UserSteps userSteps;
    private ElementsToolbar elementsToolbar;
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;

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
        userSteps.openBaseUri(webDriver);
        elementsToolbar = new ElementsToolbar(webDriver);
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        profilePage = new ProfilePage(webDriver);
        elementsToolbar.waitWhenPersonalAccountButtonToBeClickable();
        elementsToolbar.clickPersonalAccountButton();
        loginPage.fillTheFieldsLoginPage(email, password);
        loginPage.clickLoginButton();
        mainPage.waitWhenTheOrderCreateButtonIsVisible();
    }

    @Test
    @Description("Проверка перехода в личный кабинет по клику на кнопку 'Личный кабинет'")
    public void checkGoToPersonalAccountFromTheMainPage() {
        elementsToolbar.clickPersonalAccountButton();
        profilePage.waitWhenTheExitButtonIsVisible();

        Assert.assertTrue(profilePage.checkThatTheExitButtonIsDisplayed());
    }

    @Test
    @Description("Проверка перехода на главную страницу по клику на 'Конструктор'")
    public void checkClickingTheConstructorButtonInThePersonalAccount() {
        elementsToolbar.clickPersonalAccountButton();
        profilePage.waitWhenTheExitButtonIsVisible();
        elementsToolbar.clickConstructorButton();
        mainPage.waitWhenTheAssembleTheBurgerTitleIsVisible();

        Assert.assertTrue(mainPage.checkThatTheAssembleTheBurgerTitleIsDisplayed());
    }

    @Test
    @Description("Проверка перехода на главную страницу по клику на логотип Stellar Burgers")
    public void checkClickingTheStellarBurgersLogoInThePersonalAccount() {
        elementsToolbar.clickPersonalAccountButton();
        profilePage.waitWhenTheExitButtonIsVisible();
        elementsToolbar.clickStellarBurgersLogo();
        mainPage.waitWhenTheAssembleTheBurgerTitleIsVisible();

        Assert.assertTrue(mainPage.checkThatTheAssembleTheBurgerTitleIsDisplayed());
    }

    @After
    public void tearDown() {
        webDriver.quit();
        userSteps.userDelete(accessToken);
    }
}
