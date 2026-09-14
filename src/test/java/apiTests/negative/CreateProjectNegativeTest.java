package apiTests.negative;

import apiTests.based.BaseTest;
import dto.CreateProjectRequest;
import dto.Leader;
import endpoints.functionsApi.ProjectApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static specifications.ApiSpecifications.response400;

public class CreateProjectNegativeTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "   "
    })
    void shouldNotCreateProjectWithInvalidName(String name) {

        String shortName = "TP" + System.currentTimeMillis();

        CreateProjectRequest request =
                new CreateProjectRequest(
                        name,
                        shortName,
                        new Leader(currentUserId)
                );

        ProjectApi.createNewProject(request, response400());
    }
}