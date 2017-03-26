package easytests.models;

import easytests.entities.QuestionEntity;
import java.util.List;
import lombok.*;

/**
 * @author firkhraag
 */
@Data
public class QuestionModel implements QuestionModelInterface {

    private Integer id;

    private String text;

    private Integer type;

    private TopicModelInterface topic;

    private List<AnswerModelInterface> answers;

    public void map(QuestionEntity questionEntity) {
        this.setId(questionEntity.getId());
        this.setText(questionEntity.getText());
        this.setType(questionEntity.getType());
    }
}
