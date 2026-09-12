package apiTests.positive;

import apiTests.based.Base;
import endpoints.userApi.UserApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginViaApiTest extends Base {

    @Test
    @DisplayName("Проверка доступа с передачей поля login")
    void testGetProfile() {

        UserApi
                .getCurrentUser(AUTH_SPEC, "id,login")
                .then()
                .statusCode(200)
                .body("login", equalTo("admin"));
    }

    @Test
    @DisplayName("Отказ в доступе (401) без передачи токена")
    void shouldDenyAccessWithoutToken() {

        UserApi
                .getCurrentUserWithoutAuth(NO_AUTH_SPEC)
                .then()
                .statusCode(401);
    }
}

