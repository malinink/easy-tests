package easytests.core.models;

import easytests.core.entities.QuestionEntity;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.QuestionTypeModelEmpty;
import easytests.core.models.empty.TopicModelEmpty;

import java.util.List;

import lombok.*;

/**
 * @author firkhraag
 */
@Data
public class QuestionModel implements QuestionModelInterface {

    private Integer id;

    private String text;

    private QuestionTypeModelInterface questionType;

    private TopicModelInterface topic;

    private List<AnswerModelInterface> answers;

    public void map(QuestionEntity questionEntity) {
        this.setId(questionEntity.getId());
        this.setText(questionEntity.getText());
        this.setQuestionType(new QuestionTypeModelEmpty(questionEntity.getQuestionTypeId()));
        this.setTopic(new TopicModelEmpty(questionEntity.getTopicId()));
        this.setAnswers(new ModelsListEmpty());
    }
}
