package endpoints.functionsApi;

import dto.CreateCustomFieldRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import specifications.ApiSpecifications;

public class CustomFieldApi {
    private static final String CUSTOM_FIELD_FIELDS = "id,name,fieldType(presentation,id),isAutoAttached,isDisplayedInIssueList";
    private static final String API_PATH_POST = "/api/admin/customFieldSettings/customFields";
    private static final String API_PATH_DELETE = "/api/admin/customFieldSettings/customFields/{id}";

    public static Response createCustomField(CreateCustomFieldRequest request) {

        return RestAssured
                .given()
                .spec(ApiSpecifications.getAuthSpec())
                .body(request)
                .queryParam("fields",CUSTOM_FIELD_FIELDS)
                .post(API_PATH_POST);
    }

    public static Response deleteCustomField(String id) {

        return RestAssured
                .given()
                .spec(ApiSpecifications.getAuthSpec())
                .delete(API_PATH_DELETE, id);
    }
}