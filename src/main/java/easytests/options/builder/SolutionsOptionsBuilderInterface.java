package easytests.options.builder;

import easytests.options.SolutionsOptionsInterface;

/**
 * @author nikitalpopov
 */
public interface SolutionsOptionsBuilderInterface extends OptionsBuilderInterface {

    SolutionsOptionsInterface forDelete();

    SolutionsOptionsInterface forAuth();

}
