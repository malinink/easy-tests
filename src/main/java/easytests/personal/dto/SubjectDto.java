package easytests.personal.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author vkpankov
 */
@Data
public class SubjectDto {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String name;

    private String description;

    private Integer issueStandardId;

}
