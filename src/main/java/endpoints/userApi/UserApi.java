package endpoints.userApi;

import io.restassured.RestAssured;
import specifications.ApiSpecifications;

public class UserApi {
    public static String getCurrentUserId() {
        return RestAssured
                .given()
                .spec(ApiSpecifications.getAuthSpec())
                .queryParam("fields", "id")
                .get("/api/users/me")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }
}
