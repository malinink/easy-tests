package easytests.entities;

import java.util.List;

/**
 * @author malinink
 * @deprecated cause of models
 */
@Deprecated
public interface SubjectInterface extends EntityInterface {

    List<TopicInterface> getTopics();

    SubjectInterface setTopics(List<TopicInterface> topics);

    Integer getUserId();

    SubjectInterface setUserId(Integer userId);

    String getName();

    SubjectInterface setName(String name);

    IssueStandardInterface getIssueStandard();

    SubjectInterface setIssueStandard(IssueStandardInterface issueStandard);

}
