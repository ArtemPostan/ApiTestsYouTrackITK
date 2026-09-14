package apiTests.based;

import endpoints.functionsApi.CustomFieldApi;
import endpoints.functionsApi.ProjectApi;
import endpoints.userApi.UserApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {

    protected static String currentUserId;

    protected final List<String> createdProjectIds = new ArrayList<>();
    protected final List<String> createdCustomFieldIds = new ArrayList<>();

    @BeforeAll
    static void globalSetup() {
        // Если UserApi тоже переведен на внутренние спецификации, аргумент здесь больше не нужен
        currentUserId = UserApi.getCurrentUserId();
    }

    @AfterEach
    void autoCleanup() {

        for (String projectId : createdProjectIds) {
            try {
                ProjectApi.deleteProject(projectId);
            } catch (Exception e) {
                System.err.println(
                        "Не удалось удалить проект: " + projectId
                );
            }
        }

        for (String fieldId : createdCustomFieldIds) {
            try {
                CustomFieldApi.deleteCustomField(fieldId);
            } catch (Exception e) {
                System.err.println(
                        "Не удалось удалить кастомное поле: " + fieldId
                );
            }
        }
    }
}