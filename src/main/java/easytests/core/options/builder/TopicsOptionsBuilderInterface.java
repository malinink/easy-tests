package easytests.core.options.builder;

import easytests.core.options.TopicsOptionsInterface;

/**
 * @author vkpankov
 */
public interface TopicsOptionsBuilderInterface extends OptionsBuilderInterface {
    TopicsOptionsInterface forDelete();

    TopicsOptionsInterface forAuth();
}
