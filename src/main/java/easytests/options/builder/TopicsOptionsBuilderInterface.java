package easytests.options.builder;

import easytests.options.TopicsOptionsInterface;

/**
 * @author vkpankov
 */
public interface TopicsOptionsBuilderInterface extends OptionsBuilderInterface {
    TopicsOptionsInterface forDelete();

    TopicsOptionsInterface forAuth();
}
