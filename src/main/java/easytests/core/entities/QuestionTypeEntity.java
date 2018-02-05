package easytests.core.entities;

import easytests.core.models.QuestionTypeModelInterface;
import lombok.Data;


/**
 * @author Vlasovigor
 */
@Data
public class QuestionTypeEntity {

    private Integer id;

    private String name;

    public void map(QuestionTypeModelInterface questionTypeModel) {
        this.setId(questionTypeModel.getId());
        this.setName(questionTypeModel.getName());
    }
}
