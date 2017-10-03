package easytests.core.models.empty;

import easytests.core.entities.QuestionEntity;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;
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
    public QuestionTypeModelInterface getQuestionType() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestionType(QuestionTypeModelInterface questionType) {
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
