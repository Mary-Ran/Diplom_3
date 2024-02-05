package ru.yandex.praktikum.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsToolbar extends BasePage {
    private By constructorButton = By.xpath(".//*[text() = 'Конструктор']");
    private By personalAccountButton = By.xpath(".//*[text() = 'Личный Кабинет']");
    private By stellarBurgersLogo = By.className("AppHeader_header__logo__2D0X2");

    public ElementsToolbar(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Click the constructor button in toolbar")
    public void clickConstructorButton() {
        webDriver.findElement(constructorButton).click();
    }

    @Step("Click the personal account button in toolbar")
    public void clickPersonalAccountButton() {
        webDriver.findElement(personalAccountButton).click();
    }

    @Step("Wait when the personal account button in toolbar to be clickable")
    public void waitWhenPersonalAccountButtonToBeClickable() {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.elementToBeClickable(personalAccountButton));
    }

    @Step("Click stellar burgers logo in toolbar")
    public void clickStellarBurgersLogo() {
        webDriver.findElement(stellarBurgersLogo).click();
    }

}
