package easytests.entities;

import java.util.List;

/**
 * @author firkhraag
 */
public class Question implements QuestionInterface {

    private Integer id;

    private String text;

    private Integer type;

    private Integer topicId;

    private List<AnswerInterface> answers;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public QuestionInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public QuestionInterface setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public Integer getType() {
        return this.type;
    }

    @Override
    public QuestionInterface setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    public Integer getTopicId() {
        return this.topicId;
    }

    @Override
    public QuestionInterface setTopicId(Integer topicId) {
        this.topicId = topicId;
        return this;
    }

    @Override
    public List<AnswerInterface> getAnswers() {
        return this.answers;
    }

    @Override
    public QuestionInterface setAnswers(List<AnswerInterface> answers) {
        this.answers = answers;
        return this;
    }
}
