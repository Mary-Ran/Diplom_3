package ru.yandex.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.dto.UserCreateRequest;
import ru.yandex.praktikum.dto.UserLoginRequest;
import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.config.RestConfig.*;

public class UserSteps {

    @Step("Send POST request to /api/auth/register")
    public Response userCreate(UserCreateRequest userCreateRequest) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(userCreateRequest)
                .when()
                .post(USER_CREATE_ENDPOINT);
    }

    @Step("Send POST request to /api/auth/login")
    public Response userLogin(UserLoginRequest userLoginRequest) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(userLoginRequest)
                .when()
                .post(USER_LOGIN_ENDPOINT);
    }

    @Step("Send DELETE request to /api/auth/user")
    public Response userDelete(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .header("Authorization", accessToken)
                .when()
                .delete(USER_UPDATE_ENDPOINT);
    }

    @Step("Open stellar burgers website")
    public void openBaseUri(WebDriver webDriver) {
        webDriver.get(BASE_URI);
    }
}
