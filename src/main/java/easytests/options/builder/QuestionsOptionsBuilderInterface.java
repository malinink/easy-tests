package easytests.options.builder;

import easytests.options.QuestionsOptionsInterface;

/**
 * @author vkpankov
 */
public interface QuestionsOptionsBuilderInterface extends OptionsBuilderInterface {
    QuestionsOptionsInterface forDelete();

    QuestionsOptionsInterface forAuth();
}
