package apiTests.positive;

import based.ApiBase;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specifications.ApiSpecifications;

public class LoginViaApiTest extends ApiBase {
    @Test
    @DisplayName("Проверка доступа с передачей поля login")
    public void testGetProfile() {
        RestAssured.given()
                .spec(ApiSpecifications.getAuthSpec())
                .queryParam("fields", "id,login")
                .when()
                .get("/api/users/me")
                .then()
                .statusCode(200)
                .body("login", Matchers.equalTo("admin"));
    }

    @Test
    @DisplayName("Отказ в доступе (401) без передачи токена")
    public void shouldDenyAccessWithoutToken() {
        RestAssured.given()
                .header("Accept", "application/json")
                .when()
                .get("/api/users/me")
                .then()
                .statusCode(401);
    }
}
