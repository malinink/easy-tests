package easytests.core.options;

import easytests.core.models.SubjectModelInterface;
import easytests.core.services.*;

import java.util.List;

/**
 * @author malinink
 */
public interface SubjectsOptionsInterface extends OptionsInterface {

    void setSubjectsService(SubjectsServiceInterface subjectsService);

    void setUsersService(UsersServiceInterface usersService);

    void setIssueStandardsService(IssueStandardsServiceInterface issueStandardsService);

    void setTopicsService(TopicsServiceInterface topicsService);

    void setIssuesService(IssuesServiceInterface issuesService);

    SubjectsOptionsInterface withUser(UsersOptionsInterface usersOptions);

    SubjectsOptionsInterface withIssueStandard(IssueStandardsOptionsInterface issueStandardOptions);

    SubjectsOptionsInterface withIssues(IssuesOptionsInterface issuesOptions);

    SubjectsOptionsInterface withTopics(TopicsOptionsInterface topicsOptions);

    SubjectModelInterface withRelations(SubjectModelInterface subjectModel);

    List<SubjectModelInterface> withRelations(List<SubjectModelInterface> subjectsModels);

    void saveWithRelations(SubjectModelInterface subjectModel);

    void deleteWithRelations(SubjectModelInterface subjectModel);
}
