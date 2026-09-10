package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProjectRequest {

    private String name;
    private String shortName;
    private Leader leader;
}
