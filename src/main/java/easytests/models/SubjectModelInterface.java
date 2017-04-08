package easytests.models;

import easytests.entities.SubjectEntity;

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

    List<TopicModelInterface> getTopics();

    void setTopics(List<TopicModelInterface> topics);

    void map(SubjectEntity subjectEntity);

    IssueStandardModelInterface getIssueStandard();

    void setIssueStandard(IssueStandardModelInterface issueStandard);

    UserModelInterface getUser();

    void setUser(UserModelInterface user);

}
