package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCustomFieldRequest {

    private FieldType fieldType;
    private String name;
    private boolean isDisplayedInIssueList;
    private boolean isAutoAttached;
}
