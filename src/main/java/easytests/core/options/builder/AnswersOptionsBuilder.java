package easytests.core.options.builder;

import easytests.core.options.AnswersOptions;
import easytests.core.options.AnswersOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rezenbekk
 */
@Service
public class AnswersOptionsBuilder implements AnswersOptionsBuilderInterface {
    @Autowired
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @Override
    public AnswersOptionsInterface forAuth() {
        return new AnswersOptions().withQuestion(this.questionsOptionsBuilder.forAuth());
    }

    @Override
    public AnswersOptionsInterface forDelete() {
        return new AnswersOptions();
    }
}
