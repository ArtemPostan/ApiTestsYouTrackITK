package apiTests.positive;

import based.ApiBaseConfiguration;
import functionsApi.ProjectApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import userApi.UserApi;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateNewProjectTest extends ApiBaseConfiguration {
    private String projectId;

    @Test
    public void shouldCreateNewProject() {
        final String leaderId = UserApi.getCurrentUserId();

        String name = "Test Project_" + UUID.randomUUID();
        String shortName = "TP" + UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();

        Response response = ProjectApi .createNewProject(name, shortName, leaderId);

        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(name))
                .body("shortName", equalTo(shortName))
                .body("leader.id", equalTo(leaderId));

        projectId = response
                .path("id");
    }

    @AfterEach
    void tearDown() {
        if (projectId != null) {
            ProjectApi
                    .deleteProject(projectId)
                    .then()
                    .statusCode(200);
        }
    }
}
