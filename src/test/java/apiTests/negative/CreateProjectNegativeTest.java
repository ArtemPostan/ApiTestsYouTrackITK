package apiTests.negative;

import apiTests.based.Base;
import dto.CreateProjectRequest;
import dto.Leader;
import endpoints.functionsApi.ProjectApi;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CreateProjectNegativeTest extends Base {

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

        Response response = ProjectApi.createNewProject(AUTH_SPEC, request);

        response.then()
                .statusCode(400);
    }
}