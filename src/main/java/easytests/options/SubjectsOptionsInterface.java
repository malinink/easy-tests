package easytests.options;

import easytests.models.SubjectModelInterface;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.TopicsServiceInterface;
import easytests.services.UsersServiceInterface;

import java.util.List;

/**
 * @author malinink
 */
public interface SubjectsOptionsInterface extends OptionsInterface {

    void setSubjectsService(SubjectsServiceInterface subjectsService);

    void setUsersService(UsersServiceInterface usersService);

    void setIssueStandardsService(IssueStandardsServiceInterface issueStandardsService);

    void setTopicsService(TopicsServiceInterface topicsService);

    SubjectsOptionsInterface withUser(UsersOptionsInterface usersOptions);

    SubjectsOptionsInterface withIssueStandard(IssueStandardsOptionsInterface issueStandardOptions);

    SubjectsOptionsInterface withTopics(TopicsOptionsInterface topicsOptions);

    SubjectModelInterface withRelations(SubjectModelInterface subjectModel);

    List<SubjectModelInterface> withRelations(List<SubjectModelInterface> subjectsModels);

    void saveWithRelations(SubjectModelInterface subjectModel);

    void deleteWithRelations(SubjectModelInterface subjectModel);
}
