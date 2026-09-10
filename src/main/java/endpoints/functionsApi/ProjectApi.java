package endpoints.functionsApi;

import dto.CreateProjectRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import specifications.ApiSpecifications;

public class ProjectApi {
    private static final String PROJECT_FIELDS = "id,name,shortName,leader(id,name,login)";
    private static final String API_PATH_POST = "/api/admin/projects";
    private static final String API_PATH_DELETE = "/api/admin/projects/{id}";

    public static Response createNewProject(CreateProjectRequest request){

      return RestAssured
                .given()
                .spec(ApiSpecifications.getAuthSpec())
                .body(request)
                .queryParam("fields", PROJECT_FIELDS)
                .post(API_PATH_POST);
    }

    public static Response deleteProject(String id) {

        return RestAssured
                .given()
                .spec(ApiSpecifications.getAuthSpec())
                .delete(API_PATH_DELETE, id);
    }
}
