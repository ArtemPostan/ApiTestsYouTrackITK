package apiTests.positive;

import apiTests.based.Base;
import dto.CreateProjectRequest;
import dto.Leader;
import dto.ProjectResponse;
import endpoints.functionsApi.ProjectApi;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;

public class CreateNewProjectTest extends Base {

    @Test
    public void shouldCreateNewProject() {
        String name = "Test Project_" + UUID.randomUUID();
        String shortName = "TP" + UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();

        CreateProjectRequest request =
                new CreateProjectRequest(
                        name,
                        shortName,
                        new Leader(currentUserId)
                );

        ProjectResponse projectResponse = ProjectApi.createNewProject(AUTH_SPEC, request)
                .then()
                .statusCode(200)
                .extract()
                .as(ProjectResponse.class);

        Assertions.assertNotNull(projectResponse.getId());
        Assertions.assertEquals(name, projectResponse.getName());

        createdProjectIds.add(projectResponse.getId());
    }
}