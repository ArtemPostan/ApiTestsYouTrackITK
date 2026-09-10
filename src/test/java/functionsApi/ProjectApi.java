package functionsApi;

import based.ApiBaseConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProjectApi {

    public static Response createNewProject(String name, String shortName, String leaderId){

        String requestBody = """
                {
                  "name": "%s",
                  "shortName": "%s",
                  "leader": {
                    "id": "%s"
                  }
                }
                """.formatted(name, shortName, leaderId);

        return RestAssured
                .given()
                .spec(ApiBaseConfiguration.getAuthSpec())
                .body(requestBody)
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
