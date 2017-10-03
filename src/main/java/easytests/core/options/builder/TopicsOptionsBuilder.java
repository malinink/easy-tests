package easytests.core.options.builder;

import easytests.core.options.TopicsOptions;
import easytests.core.options.TopicsOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author firkhraag
 */
@Service
public class TopicsOptionsBuilder implements TopicsOptionsBuilderInterface {

    @Autowired
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Autowired
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @Override
    public TopicsOptionsInterface forDelete() {
        return new TopicsOptions().withQuestions(this.questionsOptionsBuilder.forDelete());
    }

    @Override
    public TopicsOptionsInterface forAuth() {
        return new TopicsOptions().withSubject(this.subjectsOptionsBuilder.forAuth());
    }
}
