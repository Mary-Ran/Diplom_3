package ru.yandex.praktikum;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.po.MainPage;
import ru.yandex.praktikum.steps.UserSteps;
import ru.yandex.praktikum.steps.VerificationSteps;

public class ConstructorTest {
    private WebDriver webDriver;
    private WebDriverFactory webDriverFactory;
    private VerificationSteps verificationSteps;
    private UserSteps userSteps;
    private MainPage mainPage;
    private String expectedInClassName = "current";

    @Before
    public void setup() {
        webDriverFactory = new WebDriverFactory();
        webDriver = webDriverFactory.getWebDriver();
        verificationSteps = new VerificationSteps();
        userSteps = new UserSteps();
        mainPage = new MainPage(webDriver);
        userSteps.openBaseUri(webDriver);
        mainPage.waitWhenLoginToAccountButtonToBeClickable();
    }

    @Test
    @Description("Проверка перехода к разделу 'Соусы' в конструкторе")
    public void checkTheTransitionToTheSaucesSection() {
        mainPage.clickOnTheSaucesSection();

        String actualClassName = mainPage.getClassAttributeForTheSaucesSection();
        verificationSteps.checkThatActualClassNameContainsExpectedInClassName(actualClassName, expectedInClassName);
    }

    @Test
    @Description("Проверка перехода к разделу 'Начинки' в конструкторе")
    public void checkTheTransitionToTheFillingsSection() {
        mainPage.clickOnTheFillingsSection();

        String actualClassName = mainPage.getClassAttributeForTheFillingsSection();
        verificationSteps.checkThatActualClassNameContainsExpectedInClassName(actualClassName, expectedInClassName);
    }

    @Test
    @Description("Проверка перехода к разделу 'Булки' в конструкторе")
    public void checkTheTransitionToTheBunsSection() {
        mainPage.clickOnTheSaucesSection();
        mainPage.clickOnTheBunsSection();

        String actualClassName = mainPage.getClassAttributeForTheBunsSection();
        verificationSteps.checkThatActualClassNameContainsExpectedInClassName(actualClassName, expectedInClassName);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
