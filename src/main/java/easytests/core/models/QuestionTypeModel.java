package easytests.core.models;

import easytests.core.entities.QuestionTypeEntity;
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
