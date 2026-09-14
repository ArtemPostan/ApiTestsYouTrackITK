package specifications;

import config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiSpecifications {

    public static RequestSpecification getAuthSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.getBaseUrl())
                .addHeader("Authorization", "Bearer " + Config.getToken())
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification getNoAuthSpec() {
        return new RequestSpecBuilder() .setBaseUri(Config.getBaseUrl()) .setContentType("application/json") .build();
    }

    public static ResponseSpecification response200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification response400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }
}