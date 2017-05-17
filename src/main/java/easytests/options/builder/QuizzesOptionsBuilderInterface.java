package easytests.options.builder;

import easytests.options.QuizzesOptionsInterface;

/**
 * @author nikitalpopov
 */
public interface QuizzesOptionsBuilderInterface extends OptionsBuilderInterface {

    QuizzesOptionsInterface forDelete();

    QuizzesOptionsInterface forAuth();

}
