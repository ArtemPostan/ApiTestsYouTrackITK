package apiTests.positive;

import based.ApiBaseConfiguration;
import dto.CustomFieldResponse;
import functionsApi.CustomFieldApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCustomFieldTest extends ApiBaseConfiguration {

    private String createdCustomFieldId;

    @Test
    void createCustomField() {

        String fieldName = "ZoneOfResponsibility_" + UUID.randomUUID();

        Response response =
                CustomFieldApi.createCustomField(fieldName);

        CustomFieldResponse customField =
                response
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(CustomFieldResponse.class);

        createdCustomFieldId = customField.getId();

        assertNotNull(customField.getId());
        assertEquals(fieldName, customField.getName());
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