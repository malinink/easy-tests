package easytests.core.entities;

import easytests.core.models.SubjectModelInterface;
import lombok.*;

/**
 * @author vkpankov
 */
@Data
public class SubjectEntity implements EntityInterface {

    private Integer id;

    private String name;

    private Integer userId;

    private String description;

    public void map(SubjectModelInterface subjectModel) {

        this.setId(subjectModel.getId());
        this.setName(subjectModel.getName());
        this.setDescription(subjectModel.getDescription());
        this.setUserId(subjectModel.getUser().getId());
    }

}
