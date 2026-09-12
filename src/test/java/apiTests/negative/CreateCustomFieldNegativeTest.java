package apiTests.negative;

import apiTests.based.Base;
import dto.CreateCustomFieldRequest;
import dto.FieldType;
import endpoints.functionsApi.CustomFieldApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

public class CreateCustomFieldNegativeTest extends Base {

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "CustomField:Name"
    })
    void shouldRejectInvalidFieldName(String fieldName) {

        CreateCustomFieldRequest request =
                new CreateCustomFieldRequest(
                        new FieldType("enum[1]"),
                        fieldName,
                        true,
                        false
                );

        CustomFieldApi
                .createCustomField(AUTH_SPEC, request)
                .then()
                .statusCode(400);
    }

    @Test
    void shouldNotAllowDuplicateField() {

        String fieldName =
                "ZoneOfResponsibility_" + UUID.randomUUID();

        CreateCustomFieldRequest request =
                new CreateCustomFieldRequest(
                        new FieldType("enum[1]"),
                        fieldName,
                        true,
                        false
                );

        String fieldId = CustomFieldApi
                .createCustomField(AUTH_SPEC, request)
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        createdCustomFieldIds.add(fieldId);

        CustomFieldApi
                .createCustomField(AUTH_SPEC, request)
                .then()
                .statusCode(400);
    }
}