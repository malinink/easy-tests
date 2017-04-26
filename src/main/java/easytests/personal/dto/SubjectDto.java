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

    private Integer id;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;

    private String description;

    public void map(SubjectModelInterface subjectModel) {
        this.setId(subjectModel.getId());
        this.setName(subjectModel.getName());
        this.setDescription(subjectModel.getDescription());
    }

    public void mapInto(SubjectModelInterface subjectModel) {
        subjectModel.setId(this.getId());
        subjectModel.setName(this.getName());
        subjectModel.setDescription(this.getDescription());
    }
}
