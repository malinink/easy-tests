package easytests.options.builder;

import easytests.options.IssueStandardQuestionTypeOptionsOptions;
import easytests.options.IssueStandardQuestionTypeOptionsOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardQuestionTypeOptionsOptionsBuilder
        implements IssueStandardQuestionTypeOptionsOptionsBuilderInterface {
    @Autowired
    private IssueStandardsOptionsBuilder issueStandardsOptionsBuilder;

    public IssueStandardQuestionTypeOptionsOptionsInterface forDelete() {
        return new IssueStandardQuestionTypeOptionsOptions();
    }

    public IssueStandardQuestionTypeOptionsOptionsInterface forAuth() {
        return new IssueStandardQuestionTypeOptionsOptions()
                .withIssueStandard(this.issueStandardsOptionsBuilder.forAuth());
    }
}
