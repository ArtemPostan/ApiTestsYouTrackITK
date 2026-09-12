package apiTests.based;

import endpoints.functionsApi.CustomFieldApi;
import endpoints.functionsApi.ProjectApi;
import endpoints.userApi.UserApi;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import specifications.ApiSpecifications;

import java.util.ArrayList;
import java.util.List;

public abstract class Base {

    protected static final RequestSpecification AUTH_SPEC =
            ApiSpecifications.getAuthSpec();
    protected static final RequestSpecification NO_AUTH_SPEC =
            ApiSpecifications.getNoAuthSpec();

    protected static String currentUserId;

    protected final List<String> createdProjectIds = new ArrayList<>();
    protected final List<String> createdCustomFieldIds = new ArrayList<>();

    @BeforeAll
    static void globalSetup() {
        currentUserId = UserApi.getCurrentUserId(AUTH_SPEC);
    }

    @AfterEach
    void autoCleanup() {

        for (String projectId : createdProjectIds) {
            try {
                ProjectApi.deleteProject(AUTH_SPEC, projectId)
                        .then()
                        .statusCode(200);
            } catch (Exception e) {
                System.err.println(
                        "Не удалось удалить проект: " + projectId
                );
            }
        }

        for (String fieldId : createdCustomFieldIds) {
            try {
                CustomFieldApi.deleteCustomField(AUTH_SPEC, fieldId)
                        .then()
                        .statusCode(200);
            } catch (Exception e) {
                System.err.println(
                        "Не удалось удалить кастомное поле: " + fieldId
                );
            }
        }
    }
}