package apiTests.negative;

import based.ApiBaseConfiguration;
import functionsApi.CustomFieldApi;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

public class CreateCustomFieldNegativeTest extends ApiBaseConfiguration {

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "CustomField:Name"
    })
    void shouldRejectInvalidFieldName(String fieldName) {

        CustomFieldApi
                .createCustomField(fieldName)
                .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    void shouldNotAllowDuplicateField() {

        String fieldName = "ZoneOfResponsibility_" + UUID.randomUUID();

        String fieldId = CustomFieldApi
                .createCustomField(fieldName)
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        try {
            CustomFieldApi
                    .createCustomField(fieldName)
                    .then()
                    .statusCode(400);
        } finally {
            CustomFieldApi
                    .deleteCustomField(fieldId)
                    .then()
                    .statusCode(200);
        }
    }

}
