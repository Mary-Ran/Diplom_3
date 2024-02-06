package ru.yandex.praktikum.steps;

import io.qameta.allure.Step;
import org.junit.Assert;

public class VerificationSteps {

    @Step("Check that actual class name equal expected class name")
    public void checkThatActualClassNameContainsExpectedInClassName(String actualClassName, String expectedInClassName) {
        Assert.assertTrue(actualClassName.contains(expectedInClassName));
    }

    @Step("Check that actual error text equal expected error text")
    public void checkThatActualErrorTextEqualExpectedErrorText(String expectedErrorText, String actualErrorText) {
        Assert.assertEquals(expectedErrorText, actualErrorText);
    }
}
