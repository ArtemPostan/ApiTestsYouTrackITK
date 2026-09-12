package specifications;

import config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

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
}