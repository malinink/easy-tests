package easytests.core.models.empty;

import easytests.core.entities.TopicEntity;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;

import java.util.List;


/**
 * @author malinink
 */
public class TopicModelEmpty extends AbstractModelEmpty implements TopicModelInterface {
    public TopicModelEmpty() {
        super();
    }

    public TopicModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getName() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setName(String name) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public SubjectModelInterface getSubject() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setSubject(SubjectModelInterface subject) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<QuestionModelInterface> getQuestions() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestions(List<QuestionModelInterface> questions) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(TopicEntity topicEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
