package ru.yandex.praktikum;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.dto.UserCreateRequest;
import ru.yandex.praktikum.po.ElementsToolbar;
import ru.yandex.praktikum.po.LoginPage;
import ru.yandex.praktikum.po.MainPage;
import ru.yandex.praktikum.po.ProfilePage;
import ru.yandex.praktikum.steps.UserSteps;

public class LogoutTest {
    private WebDriver webDriver;
    private WebDriverFactory webDriverFactory;
    private String name;
    private String email;
    private String password;
    private String accessToken;
    private UserSteps userSteps;

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
    }

    @Test
    public void checkLogoutUsingTheExitButton() {
        ElementsToolbar elementsToolbar = new ElementsToolbar(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        MainPage mainPage = new MainPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        elementsToolbar.waitWhenPersonalAccountButtonToBeClickable();
        elementsToolbar.clickPersonalAccountButton();
        loginPage.fillTheFieldsLoginPage(email, password);
        loginPage.clickLoginButton();
        mainPage.waitWhenTheOrderCreateButtonIsVisible();
        elementsToolbar.clickPersonalAccountButton();
        profilePage.waitWhenTheExitButtonIsVisible();
        profilePage.clickExitButton();

        loginPage.waitWhenTheLoginTitleIsVisible();
        loginPage.checkThatTheLoginTitleIsDisplayed();
    }

    @After
    public void tearDown() {
        webDriver.quit();
        userSteps.userDelete(accessToken);
    }
}
