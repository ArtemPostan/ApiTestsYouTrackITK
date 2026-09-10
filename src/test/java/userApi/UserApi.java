package userApi;

import based.ApiBaseConfiguration;
import io.restassured.RestAssured;

public class UserApi {
    public static String getCurrentUserId() {
        return RestAssured
                .given()
                .spec(ApiBaseConfiguration.getAuthSpec())
                .queryParam("fields", "id")
                .get("/api/users/me")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }
}
