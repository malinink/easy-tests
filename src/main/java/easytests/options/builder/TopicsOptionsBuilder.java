package easytests.options.builder;

import easytests.options.TopicsOptions;
import easytests.options.TopicsOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vkpankov
 */
@Service
public class TopicsOptionsBuilder implements TopicsOptionsBuilderInterface {

    @Autowired
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @Override
    public TopicsOptionsInterface forDelete() {
        return new TopicsOptions().withQuestions(questionsOptionsBuilder.forDelete());
    }

    @Override
    public TopicsOptionsInterface forAuth() {
        return new TopicsOptions().withQuestions(questionsOptionsBuilder.forAuth());
    }
}
