package easytests.options.builder;

import easytests.options.IssueStandardTopicPrioritiesOptions;
import easytests.options.IssueStandardTopicPrioritiesOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardTopicPrioritiesOptionsBuilder
        implements IssueStandardTopicPrioritiesOptionsBuilderInterface {
    @Autowired
    private IssueStandardsOptionsBuilder issueStandardsOptionsBuilder;

    public IssueStandardTopicPrioritiesOptionsInterface forDelete() {
        return new IssueStandardTopicPrioritiesOptions();
    }

    public IssueStandardTopicPrioritiesOptionsInterface forAuth() {
        return new IssueStandardTopicPrioritiesOptions()
                .withIssueStandard(this.issueStandardsOptionsBuilder.forAuth());
    }
}
