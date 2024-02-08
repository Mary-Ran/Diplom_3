package ru.yandex.praktikum.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {
    private By exitButton = By.xpath(".//button[text() = 'Выход']");

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Click the exit button in the profile page")
    public void clickExitButton() {
        webDriver.findElement(exitButton).click();
    }

    @Step("Check that the exit button is displayed")
    public boolean checkThatTheExitButtonIsDisplayed() {
        return webDriver.findElement(exitButton).isDisplayed();
    }

    @Step("Wait when the exit button is visible")
    public void waitWhenTheExitButtonIsVisible() {
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }
}
