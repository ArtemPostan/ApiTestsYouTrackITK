package apiTests.negative;

import dto.CreateProjectRequest;
import dto.Leader;
import functionsApi.ProjectApi;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import userApi.UserApi;

public class CreateProjectNegativeTest {
    private final String leaderId = UserApi.getCurrentUserId();

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
                        new Leader(leaderId)
                );

        Response response = ProjectApi.createNewProject(request);

        response.then()
                .statusCode(400);
    }
}
