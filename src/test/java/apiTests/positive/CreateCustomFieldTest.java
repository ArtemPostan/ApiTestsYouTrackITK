package apiTests.positive;

import based.ApiBase;
import dto.CreateCustomFieldRequest;
import dto.CustomFieldResponse;
import dto.FieldType;
import endpoints.functionsApi.CustomFieldApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCustomFieldTest extends ApiBase {

    private String createdCustomFieldId;

    @Test
    void createCustomField() {

        String fieldName = "ZoneOfResponsibility_" + UUID.randomUUID();

        CreateCustomFieldRequest request =
                new CreateCustomFieldRequest(
                        new FieldType("enum[1]"),
                        fieldName,
                        true,
                        false
                );
        Response response =
                CustomFieldApi.createCustomField(request);


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