package easytests.options.builder;

import easytests.options.TopicsOptions;
import easytests.options.TopicsOptionsInterface;
import org.springframework.stereotype.Service;

/**
 * @author firkhraag
 */
@Service
public class TopicsOptionsBuilder {

    public TopicsOptionsInterface forDelete() {
        return new TopicsOptions();
    }

    public TopicsOptionsInterface forAuth() {
        return new TopicsOptions();
    }
}
