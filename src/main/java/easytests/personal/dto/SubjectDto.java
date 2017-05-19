package easytests.personal.dto;

import easytests.models.SubjectModelInterface;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author vkpankov
 */
@Data
public class SubjectDto {

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;

    private String description;

    public void map(SubjectModelInterface subjectModel) {
        this.setName(subjectModel.getName());
        this.setDescription(subjectModel.getDescription());
    }

    public void mapInto(SubjectModelInterface subjectModel) {
        subjectModel.setName(this.getName());
        subjectModel.setDescription(this.getDescription());
    }
}
