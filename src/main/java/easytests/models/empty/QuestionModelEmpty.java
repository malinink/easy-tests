package easytests.models.empty;

import easytests.entities.QuestionEntity;
import easytests.models.AnswerModelInterface;
import easytests.models.QuestionModelInterface;
import easytests.models.TopicModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import java.util.List;


/**
 * @author firkhraag
 */
public class QuestionModelEmpty extends AbstractModelEmpty implements QuestionModelInterface {
    public QuestionModelEmpty() {
        super();
    }

    public QuestionModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getText() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setText(String text) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getType() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setType(Integer type) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public TopicModelInterface getTopic() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setTopic(TopicModelInterface topic) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<AnswerModelInterface> getAnswers() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setAnswers(List<AnswerModelInterface> answers) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(QuestionEntity questionEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
