package apiTests.positive;

import based.ApiBaseConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import functionsApi.CustomFieldApi;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class CreateCustomFieldTest extends ApiBaseConfiguration {
    private String createdCustomFieldId;

    @Test
    void createCustomField() {
        String fieldName = "ZoneOfResponsibility_" + UUID.randomUUID();
        createdCustomFieldId =
                CustomFieldApi
                        .createCustomField(fieldName)
                        .then()
                        .statusCode(200)
                        .body("name", equalTo(fieldName))
                        .extract()
                        .path("id");
    }

    @AfterEach
    void tearDown() {

        if (createdCustomFieldId != null) {
            CustomFieldApi
                    .deleteCustomField(createdCustomFieldId)
                    .then()
                    .statusCode(200);
        }
    }

}