package easytests.personal.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author vkpankov
 */
@Data
public class SubjectDto {

    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    private String description;

    private Integer issueStandardId;

}
