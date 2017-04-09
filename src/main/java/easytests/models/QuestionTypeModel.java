package easytests.models;

import easytests.entities.QuestionTypeEntity;
import lombok.Data;


/**
 * @author malinink
 */
@Data
public class QuestionTypeModel implements QuestionTypeModelInterface {
    private Integer id;

    private String name;

    public void map(QuestionTypeEntity questionTypeEntity) {
        this.setId(questionTypeEntity.getId());
        this.setName(questionTypeEntity.getName());
    }
}
