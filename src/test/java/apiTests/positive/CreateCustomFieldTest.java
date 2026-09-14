package apiTests.positive;


import apiTests.based.BaseTest;
import dto.CreateCustomFieldRequest;
import dto.CustomFieldResponse;
import dto.FieldType;
import endpoints.functionsApi.CustomFieldApi;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCustomFieldTest extends BaseTest {

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

        CustomFieldResponse customField = CustomFieldApi.createCustomField(request);

        assertNotNull(customField.getId());
        assertEquals(fieldName, customField.getName());

        createdCustomFieldIds.add(customField.getId());
    }
}

