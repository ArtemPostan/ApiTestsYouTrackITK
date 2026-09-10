package apiTests.negative;

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

        Response response = ProjectApi.createNewProject(
                name,
                shortName,
                leaderId
        );

        response.then()
                .statusCode(400);
    }
}
