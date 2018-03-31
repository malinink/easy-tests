package easytests.core.options;

import easytests.core.models.IssueStandardModelInterface;
import easytests.core.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.core.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.core.services.IssueStandardsServiceInterface;
import easytests.core.services.SubjectsServiceInterface;

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
