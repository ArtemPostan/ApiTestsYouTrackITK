package apiTests.positive;

import apiTests.based.BaseTest;
import endpoints.userApi.UserApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginViaApiTest extends BaseTest {

    @Test
    @DisplayName("Проверка доступа с передачей поля login")
    void testGetProfile() {
        String login = UserApi.getUserLogin("id,login");

        assertEquals("admin", login);
    }

    @Test
    @DisplayName("Отказ в доступе (401) без передачи токена")
    void shouldDenyAccessWithoutToken() {
        int statusCode = UserApi.getStatusCodeWithoutAuth();

        assertEquals(401, statusCode);
    }
}