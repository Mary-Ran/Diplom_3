package ru.yandex.praktikum;

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
    private final String expectedClassName = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

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
    public void checkTheTransitionToTheSaucesSection() {
        mainPage.clickOnTheSaucesSection();

        String actualClassName = mainPage.getClassAttributeForTheSaucesSection();
        verificationSteps.checkThatActualClassNameEqualExpectedClassName(expectedClassName, actualClassName);
    }

    @Test
    public void checkTheTransitionToTheFillingsSection() {
        mainPage.clickOnTheFillingsSection();

        String actualClassName = mainPage.getClassAttributeForTheFillingsSection();
        verificationSteps.checkThatActualClassNameEqualExpectedClassName(expectedClassName, actualClassName);
    }

    @Test
    public void checkTheTransitionToTheBunsSection() {
        mainPage.clickOnTheSaucesSection();
        mainPage.clickOnTheBunsSection();

        String actualClassName = mainPage.getClassAttributeForTheBunsSection();
        verificationSteps.checkThatActualClassNameEqualExpectedClassName(expectedClassName, actualClassName);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
