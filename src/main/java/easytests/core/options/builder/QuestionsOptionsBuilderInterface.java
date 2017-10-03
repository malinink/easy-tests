package easytests.core.options.builder;

import easytests.core.options.QuestionsOptionsInterface;

/**
 * @author vkpankov
 */
public interface QuestionsOptionsBuilderInterface extends OptionsBuilderInterface {
    QuestionsOptionsInterface forDelete();

    QuestionsOptionsInterface forAuth();
}
