package easytests.models.empty;

import easytests.entities.TopicEntity;
import easytests.models.QuestionModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.models.TopicModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
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
