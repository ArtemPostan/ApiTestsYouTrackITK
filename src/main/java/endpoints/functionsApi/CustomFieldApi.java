package endpoints.functionsApi;

import dto.CreateCustomFieldRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomFieldApi {

    private static final String CUSTOM_FIELD_FIELDS =
            "id,name,fieldType(presentation,id),isAutoAttached,isDisplayedInIssueList";

    private static final String API_PATH_POST =
            "/api/admin/customFieldSettings/customFields";

    private static final String API_PATH_DELETE =
            "/api/admin/customFieldSettings/customFields/{id}";

    public static Response createCustomField(
            RequestSpecification spec,
            CreateCustomFieldRequest request) {

        return RestAssured
                .given()
                .spec(spec)
                .body(request)
                .queryParam("fields", CUSTOM_FIELD_FIELDS)
                .post(API_PATH_POST);
    }

    public static Response deleteCustomField(
            RequestSpecification spec,
            String id) {

        return RestAssured
                .given()
                .spec(spec)
                .delete(API_PATH_DELETE, id);
    }
}