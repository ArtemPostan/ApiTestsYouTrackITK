package endpoints.userApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApi {

    public static String getCurrentUserId(RequestSpecification spec) {

        return RestAssured
                .given()
                .spec(spec)
                .queryParam("fields", "id")
                .get("/api/users/me")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }

    public static Response getCurrentUser(
            RequestSpecification spec,
            String fields) {

        return RestAssured
                .given()
                .spec(spec)
                .queryParam("fields", fields)
                .get("/api/users/me");
    }

    public static Response getCurrentUserWithoutAuth(
            RequestSpecification spec) {

        return RestAssured
                .given()
                .spec(spec)
                .header("Accept", "application/json")
                .get("/api/users/me");
    }
}

