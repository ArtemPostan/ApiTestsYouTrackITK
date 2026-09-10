package dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomFieldResponse {

    private String id;
    private String name;
    private FieldType fieldType;
    @JsonProperty("isDisplayedInIssueList")
    private boolean displayedInIssueList;

    @JsonProperty("isAutoAttached")
    private boolean autoAttached;
}
