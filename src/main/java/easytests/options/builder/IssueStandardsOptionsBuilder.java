package easytests.options.builder;

import easytests.options.IssueStandardsOptions;
import easytests.options.IssueStandardsOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardsOptionsBuilder implements IssueStandardsOptionsBuilderInterface {
    @Autowired
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Autowired
    private IssueStandardTopicPrioritiesOptionsBuilder topicPrioritiesOptionsBuilder;

    @Autowired
    private IssueStandardQuestionTypeOptionsOptionsBuilder questionTypeOptionsOptionsBuilder;

    @Override
    public IssueStandardsOptionsInterface forDelete() {
        return new IssueStandardsOptions()
                .withTopicPriorities(this.topicPrioritiesOptionsBuilder.forDelete())
                .withQuestionTypeOptions(this.questionTypeOptionsOptionsBuilder.forDelete());
    }

    @Override
    public IssueStandardsOptionsInterface forAuth() {
        return new IssueStandardsOptions().withSubject(this.subjectsOptionsBuilder.forAuth());
    }
}
