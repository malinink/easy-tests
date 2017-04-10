package easytests.options;

import easytests.models.IssueStandardModelInterface;
import easytests.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.SubjectsServiceInterface;
import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardsOptionsInterface extends OptionsInterface {

    void setIssueStandardsService(IssueStandardsServiceInterface issueStandardsService);

    void setTopicPrioritiesService(IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService);

    void setQuestionTypeOptionsService(IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService);

    void setSubjectsService(SubjectsServiceInterface subjectsService);

    IssueStandardsOptionsInterface withTopicPriorities(
            IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);

    IssueStandardsOptionsInterface withQuestionTypeOptions(
            IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);

    IssueStandardsOptionsInterface withSubject(SubjectsOptionsInterface subjectsOptions);

    IssueStandardModelInterface withRelations(IssueStandardModelInterface issueStandardModel);

    List<IssueStandardModelInterface> withRelations(List<IssueStandardModelInterface> issueStandardModels);

    void saveWithRelations(IssueStandardModelInterface issueStandardModel);

    void deleteWithRelations(IssueStandardModelInterface issueStandardModel);
}
