package easytests.options.builder;

import easytests.options.AnswersOptions;
import easytests.options.AnswersOptionsInterface;
import org.springframework.stereotype.Service;

/**
 * @author firkhraag
 */
@Service
public class AnswersOptionsBuilder {

    public AnswersOptionsInterface forDelete() {
        return new AnswersOptions();
    }

    public AnswersOptionsInterface forAuth() {
        return new AnswersOptions();
    }
}
