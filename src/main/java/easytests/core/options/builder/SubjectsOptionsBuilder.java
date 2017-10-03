package easytests.core.options.builder;

import easytests.core.options.SubjectsOptions;
import easytests.core.options.SubjectsOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author malinink
 */
@Service
public class SubjectsOptionsBuilder implements SubjectsOptionsBuilderInterface {
    @Autowired
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @Autowired
    private UsersOptionsBuilder usersOptionsBuilder;

    @Autowired
    private IssueStandardsOptionsBuilder issueStandardsOptionsBuilder;

    @Override
    public SubjectsOptionsInterface forDelete() {
        return new SubjectsOptions()
                .withTopics(this.topicsOptionsBuilder.forDelete())
                .withIssueStandard(this.issueStandardsOptionsBuilder.forDelete());
    }

    @Override
    public SubjectsOptionsInterface forAuth() {
        return new SubjectsOptions().withUser(this.usersOptionsBuilder.forAuth());
    }
}
