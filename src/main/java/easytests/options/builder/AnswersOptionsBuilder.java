package easytests.options.builder;

import easytests.options.AnswersOptions;
import easytests.options.AnswersOptionsInterface;
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
}
