package ru.yandex.praktikum.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    private By loginToAccountButton = By.xpath(".//*[text() = 'Войти в аккаунт']");
    private By orderCreateButton = By.xpath(".//*[text() = 'Оформить заказ']");
    private By assembleTheBurgerTitle = By.xpath(".//*[text() = 'Соберите бургер']");
    private By bunsSection = By.xpath(".//*[@class = 'text text_type_main-default' and text() = 'Булки']");
    private By saucesSection = By.xpath(".//*[@class = 'text text_type_main-default' and text() = 'Соусы']");
    private By fillingsSection = By.xpath(".//*[@class = 'text text_type_main-default' and text() = 'Начинки']");
    private By parentSaucesSection = By.xpath(".//*[@class = 'text text_type_main-default' and text() = 'Соусы']/parent::div");
    private By parentFillingsSections = By.xpath(".//*[@class = 'text text_type_main-default' and text() = 'Начинки']/parent::div");
    private By parentBunsSection = By.xpath(".//*[@class = 'text text_type_main-default' and text() = 'Булки']/parent::div");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Click the login to account button in the main page")
    public void clickLoginToAccountButton() {
        webDriver.findElement(loginToAccountButton).click();
    }

    @Step("Check that the order create button is displayed")
    public boolean checkThatTheOrderCreateButtonIsDisplayed() {
        return webDriver.findElement(orderCreateButton).isDisplayed();
    }

    @Step("Wait when the order create button is visible")
    public void waitWhenTheOrderCreateButtonIsVisible() {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderCreateButton));
    }

    @Step("Check that the assemble the burger title is displayed")
    public boolean checkThatTheAssembleTheBurgerTitleIsDisplayed() {
        return webDriver.findElement(assembleTheBurgerTitle).isDisplayed();
    }

    @Step("Wait when the assemble the burger title is visible")
    public void waitWhenTheAssembleTheBurgerTitleIsVisible() {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(assembleTheBurgerTitle));
    }

    @Step("Wait when the login to account button to be clickable")
    public void waitWhenLoginToAccountButtonToBeClickable() {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginToAccountButton));
    }

    @Step("Click on the buns section in the main page")
    public void clickOnTheBunsSection() {
        webDriver.findElement(bunsSection).click();
    }

    @Step("Click on the sauces section in the main page")
    public void clickOnTheSaucesSection() {
        webDriver.findElement(saucesSection).click();
    }

    @Step("Click on the fillings section in the main page")
    public void clickOnTheFillingsSection() {
        webDriver.findElement(fillingsSection).click();
    }

    @Step("Get class attribute for the sauces section")
    public String getClassAttributeForTheSaucesSection() {
        return webDriver.findElement(parentSaucesSection).getAttribute("class");
    }

    @Step("Get class attribute for the fillings section")
    public String getClassAttributeForTheFillingsSection() {
        return webDriver.findElement(parentFillingsSections).getAttribute("class");
    }

    @Step("Get class attribute for the buns section")
    public String getClassAttributeForTheBunsSection() {
        return webDriver.findElement(parentBunsSection).getAttribute("class");
    }
}
