package apiTests.positive;

import apiTests.based.BaseTest;
import dto.CreateProjectRequest;
import dto.Leader;
import dto.ProjectResponse;
import endpoints.functionsApi.ProjectApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class CreateNewProjectTest extends BaseTest {

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

        ProjectResponse createdProject = ProjectApi.createNewProject(request);
        String projectId = createdProject.getId();

        Assertions.assertNotNull(projectId);
        createdProjectIds.add(projectId);

        ProjectResponse fetchedProject = ProjectApi.getProjectById(projectId);

        Assertions.assertEquals(name, fetchedProject.getName());
    }
}