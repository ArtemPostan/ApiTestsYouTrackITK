package endpoints.functionsApi;

import dto.CreateCustomFieldRequest;
import dto.CustomFieldResponse;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static specifications.ApiSpecifications.getAuthSpec;
import static specifications.ApiSpecifications.response200;

public class CustomFieldApi {

    private static final String CUSTOM_FIELD_FIELDS = "id,name,fieldType(presentation,id),isAutoAttached,isDisplayedInIssueList";
    private static final String API_PATH_POST = "/api/admin/customFieldSettings/customFields";
    private static final String API_PATH_DELETE = "/api/admin/customFieldSettings/customFields/{id}";

    public static CustomFieldResponse createCustomField(CreateCustomFieldRequest request) {
        return given()
                .spec(getAuthSpec())
                .body(request)
                .queryParam("fields", CUSTOM_FIELD_FIELDS)
                .post(API_PATH_POST)
                .then()
                .spec(response200())
                .extract()
                .as(CustomFieldResponse.class);
    }

    public static void deleteCustomField(String id) {
        given()
                .spec(getAuthSpec())
                .delete(API_PATH_DELETE, id)
                .then()
                .spec(response200());
    }

    public static Response createCustomField(CreateCustomFieldRequest request, ResponseSpecification responseSpec) {
        return given()
                .spec(getAuthSpec())
                .body(request)
                .queryParam("fields", CUSTOM_FIELD_FIELDS)
                .post(API_PATH_POST)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }
}