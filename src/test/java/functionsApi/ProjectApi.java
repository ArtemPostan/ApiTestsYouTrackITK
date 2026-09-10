package functionsApi;

import based.ApiBaseConfiguration;
import dto.CreateProjectRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProjectApi {

    public static Response createNewProject(CreateProjectRequest request){

      return RestAssured
                .given()
                .spec(ApiBaseConfiguration.getAuthSpec())
                .body(request)
                .queryParam("fields", "id,name,shortName,leader(id,name,login)")
                .post("/api/admin/projects");
    }

    public static Response deleteProject(String id) {

        return RestAssured
                .given()
                .spec(ApiBaseConfiguration.getAuthSpec())
                .delete("/api/admin/projects/{id}", id);
    }
}
