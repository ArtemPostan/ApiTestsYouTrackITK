package apiTests.negative;

import apiTests.based.BaseTest;
import dto.CreateCustomFieldRequest;
import dto.CustomFieldResponse;
import dto.FieldType;
import endpoints.functionsApi.CustomFieldApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static specifications.ApiSpecifications.response400;

public class CreateCustomFieldNegativeTest extends BaseTest {

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

        CustomFieldApi.createCustomField(request, response400());
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

        // Первое создание (успешно, возвращает DTO)
        CustomFieldResponse customField = CustomFieldApi.createCustomField(request);
        createdCustomFieldIds.add(customField.getId());

        // Второе создание (ожидаем ошибку 400)
        CustomFieldApi.createCustomField(request, response400());
    }
}