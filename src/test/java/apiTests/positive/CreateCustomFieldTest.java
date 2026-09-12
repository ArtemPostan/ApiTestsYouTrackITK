package apiTests.positive;


import apiTests.based.Base;
import dto.CreateCustomFieldRequest;
import dto.CustomFieldResponse;
import dto.FieldType;
import endpoints.functionsApi.CustomFieldApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCustomFieldTest extends Base {

    @Test
    void createCustomField() {

        String fieldName =
                "ZoneOfResponsibility_" + UUID.randomUUID();

        CreateCustomFieldRequest request =
                new CreateCustomFieldRequest(
                        new FieldType("enum[1]"),
                        fieldName,
                        true,
                        false
                );

        Response response =
                CustomFieldApi.createCustomField(
                        AUTH_SPEC,
                        request
                );

        CustomFieldResponse customField =
                response
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(CustomFieldResponse.class);

        assertNotNull(customField.getId());
        assertEquals(fieldName, customField.getName());

        createdCustomFieldIds.add(customField.getId());
    }
}

