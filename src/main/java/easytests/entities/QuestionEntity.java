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

    private Integer questionTypeId;

    private Integer topicId;

    public void map(QuestionModelInterface questionModel) {
        this.setId(questionModel.getId());
        this.setText(questionModel.getText());
        this.setQuestionTypeId(questionModel.getQuestionType().getId());
        this.setTopicId(questionModel.getTopic().getId());
    }
}
