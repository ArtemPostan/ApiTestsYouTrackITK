package functionsApi;

import based.ApiBaseConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CustomFieldApi {

    public static Response createCustomField(String name) {

        String requestBody = """
                {
                  "fieldType": {
                    "id": "enum[1]"
                  },
                  "name": "%s",
                  "isDisplayedInIssueList": true,
                  "isAutoAttached": false
                }
                """.formatted(name);

        return RestAssured
                .given()
                .spec(ApiBaseConfiguration.getAuthSpec())
                .body(requestBody)
                .queryParam("fields",
                        "id,name,fieldType(presentation,id),isAutoAttached,isDisplayedInIssueList")
                .post("/api/admin/customFieldSettings/customFields");
    }

    public static Response deleteCustomField(String id) {

        return RestAssured
                .given()
                .spec(ApiBaseConfiguration.getAuthSpec())
                .delete("/api/admin/customFieldSettings/customFields/{id}", id);
    }
}
