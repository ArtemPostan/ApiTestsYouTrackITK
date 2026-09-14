package endpoints.userApi;

import static io.restassured.RestAssured.given;
import static specifications.ApiSpecifications.getAuthSpec;
import static specifications.ApiSpecifications.getNoAuthSpec;
import static specifications.ApiSpecifications.response200;

public class UserApi {

    public static String getCurrentUserId() {
        return given()
                .spec(getAuthSpec())
                .queryParam("fields", "id")
                .get("/api/users/me")
                .then()
                .spec(response200())
                .extract()
                .path("id");
    }

    public static String getUserLogin(String fields) {
        return given()
                .spec(getAuthSpec())
                .queryParam("fields", fields)
                .get("/api/users/me")
                .then()
                .spec(response200())
                .extract()
                .path("login");
    }

    public static int getStatusCodeWithoutAuth() {
        return given()
                .spec(getNoAuthSpec())
                .header("Accept", "application/json")
                .get("/api/users/me")
                .statusCode();
    }
}