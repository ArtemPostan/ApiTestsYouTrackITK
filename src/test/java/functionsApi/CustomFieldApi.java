package functionsApi;

import based.ApiBaseConfiguration;
import dto.CreateCustomFieldRequest;
import dto.FieldType;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CustomFieldApi {

    public static Response createCustomField(String name) {

        CreateCustomFieldRequest request =
                new CreateCustomFieldRequest(
                        new FieldType("enum[1]"),
                        name,
                        true,
                        false
                );

        return RestAssured
                .given()
                .spec(ApiBaseConfiguration.getAuthSpec())
                .body(request)
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