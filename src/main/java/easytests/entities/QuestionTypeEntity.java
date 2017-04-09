package easytests.entities;

import easytests.models.QuestionTypeModelInterface;
import lombok.Data;


/**
 * @author malinink
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
