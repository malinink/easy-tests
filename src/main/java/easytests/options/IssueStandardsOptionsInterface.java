package easytests.options;

import easytests.models.IssueStandardModelInterface;

/**
 * @author vkpankov
 */
public interface IssueStandardsOptionsInterface {

    IssueStandardModelInterface withRelations(IssueStandardModelInterface issueStandardModel);

    void saveWithRelations(IssueStandardModelInterface issueStandardModel);

    void deleteWithRelations(IssueStandardModelInterface issueStandardModel);

}
