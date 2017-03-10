package easytests.entities;

import easytests.models.QuestionModelInterface;
import lombok.*;

/**
 * @author firkhraag
 */
@Data
public class QuestionEntity {

    private Integer id;

    private String text;

    private Integer type;

    private Integer topicId;

    public void map(QuestionModelInterface questionModel) {
        this.setId(questionModel.getId());
        this.setText(questionModel.getText());
        this.setType(questionModel.getType());
        this.setTopicId(questionModel.getTopicId());
    }
}
