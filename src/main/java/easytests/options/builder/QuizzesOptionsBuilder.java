package easytests.options.builder;

import easytests.options.QuizzesOptions;
import easytests.options.QuizzesOptionsInterface;
import easytests.options.TesteesOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fortyways
 */
@Service
public class QuizzesOptionsBuilder implements QuizzesOptionsBuilderInterface {

    @Autowired
    private IssuesOptionsBuilder issuesOptionsBuilder;

    @Override
    public QuizzesOptionsInterface forAuth() {
        return new QuizzesOptions().withIssue(issuesOptionsBuilder.forAuth());
    }

    @Override
    public QuizzesOptionsInterface forDelete() {
        return new QuizzesOptions()
                .withTestee(new TesteesOptions());
    }
}
