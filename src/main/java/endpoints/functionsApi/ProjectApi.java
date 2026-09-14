package endpoints.functionsApi;

import dto.CreateProjectRequest;
import dto.ProjectResponse;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static specifications.ApiSpecifications.getAuthSpec;
import static specifications.ApiSpecifications.response200;

public class ProjectApi {

    private static final String API_PATH_POST = "/api/admin/projects";
    private static final String API_PATH_DELETE = "/api/admin/projects/{id}";
    private static final String PROJECT_FIELDS = "id,name,shortName,leader(id)";

    public static ProjectResponse createNewProject(CreateProjectRequest request) {
        return given()
                .spec(getAuthSpec())
                .body(request)
                .post(API_PATH_POST)
                .peek()
                .then()
                .spec(response200())
                .extract()
                .as(ProjectResponse.class);
    }

    public static void deleteProject(String id) {
        given()
                .spec(getAuthSpec())
                .delete(API_PATH_DELETE, id)
                .then()
                .spec(response200());
    }

    public static Response createNewProject(CreateProjectRequest request, ResponseSpecification responseSpec) {
        return given()
                .spec(getAuthSpec())
                .body(request)
                .post(API_PATH_POST)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public static ProjectResponse getProjectById(String id) {
        return given()
                .spec(getAuthSpec())
                .queryParam("fields", PROJECT_FIELDS)
                .get(API_PATH_DELETE, id) // Путь /api/admin/projects/{id}
                .then()
                .spec(response200())
                .extract()
                .as(ProjectResponse.class);
    }
}