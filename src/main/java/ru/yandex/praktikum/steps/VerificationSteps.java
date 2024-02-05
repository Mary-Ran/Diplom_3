package ru.yandex.praktikum.steps;

import io.qameta.allure.Step;
import org.junit.Assert;

public class VerificationSteps {

    @Step("Check that actual class name equal expected class name")
    public void checkThatActualClassNameEqualExpectedClassName(String expectedClassName, String actualClassName) {
        Assert.assertEquals(expectedClassName, actualClassName);
    }

    @Step("Check that actual error text equal expected error text")
    public void checkThatActualErrorTextEqualExpectedErrorText(String expectedErrorText, String actualErrorText) {
        Assert.assertEquals(expectedErrorText, actualErrorText);
    }
}
