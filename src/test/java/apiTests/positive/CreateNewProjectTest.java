package apiTests.positive;

import based.ApiBaseConfiguration;
import dto.CreateProjectRequest;
import dto.Leader;
import dto.ProjectResponse;
import functionsApi.ProjectApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import userApi.UserApi;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        CreateProjectRequest request =
                new CreateProjectRequest(
                        name,
                        shortName,
                        new Leader(leaderId)
                );

        Response response = ProjectApi.createNewProject(request);

        ProjectResponse projectResponse = response
                .then()
                .statusCode(200)
                .extract()
                .as(ProjectResponse.class);

        assertNotNull(projectResponse.getId());
        assertEquals(name, projectResponse.getName());
        assertEquals(shortName, projectResponse.getShortName());
        assertEquals(leaderId, projectResponse.getLeader().getId());

        projectId = projectResponse.getId();
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
