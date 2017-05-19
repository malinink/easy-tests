package easytests.options.builder;

import easytests.options.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author firkhraag
 */
@Service
public class QuestionsOptionsBuilder implements QuestionsOptionsBuilderInterface {
    @Autowired
    private AnswersOptionsBuilder answersOptionsBuilder;

    @Autowired
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @Override
    public QuestionsOptionsInterface forDelete() {
        return new QuestionsOptions().withAnswers(this.answersOptionsBuilder.forDelete());
    }

    @Override
    public QuestionsOptionsInterface forAuth() {
        return new QuestionsOptions().withTopic(this.topicsOptionsBuilder.forAuth());
    }
}
