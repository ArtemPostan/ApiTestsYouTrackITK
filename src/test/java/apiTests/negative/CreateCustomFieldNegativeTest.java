package apiTests.negative;

import based.ApiBase;
import dto.CreateCustomFieldRequest;
import dto.FieldType;
import endpoints.functionsApi.CustomFieldApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import specifications.ApiSpecifications;

import java.util.UUID;

public class CreateCustomFieldNegativeTest extends ApiSpecifications {

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
                .createCustomField(request)
                .then()
                .log().all()
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
                .createCustomField(request)
                .then()
                .statusCode(200)
                .extract()
                .path("id");
        try {
            CustomFieldApi
                    .createCustomField(request)
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