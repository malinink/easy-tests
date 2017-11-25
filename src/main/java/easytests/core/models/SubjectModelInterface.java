package easytests.core.models;

import easytests.core.entities.SubjectEntity;
import java.util.List;


/**
 * @author malinink
 */
public interface SubjectModelInterface extends ModelInterface {

    void setId(Integer id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    UserModelInterface getUser();

    void setUser(UserModelInterface user);

    List<TopicModelInterface> getTopics();

    void setTopics(List<TopicModelInterface> topics);

    IssueStandardModelInterface getIssueStandard();

    void setIssueStandard(IssueStandardModelInterface issueStandard);

    List<IssueModelInterface> getIssues();

    void setIssues(List<IssueModelInterface> issues);

    void map(SubjectEntity subjectEntity);

}
