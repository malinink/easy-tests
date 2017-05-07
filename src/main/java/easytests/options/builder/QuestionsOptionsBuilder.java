package easytests.options.builder;

import easytests.options.QuestionsOptionsInterface;
import org.springframework.stereotype.Service;

/**
 * @author vkpankov
 */
@Service
public class QuestionsOptionsBuilder implements QuestionsOptionsBuilderInterface {

    @Override
    public QuestionsOptionsInterface forDelete() {
        // TODO Firkhraag
        throw new UnsupportedOperationException();
    }

    @Override
    public QuestionsOptionsInterface forAuth() {
        // TODO Firkhraag
        throw new UnsupportedOperationException();
    }
}
