package easytests.core.options.builder;

import easytests.core.options.SolutionsOptionsInterface;

/**
 * @author nikitalpopov
 */
public interface SolutionsOptionsBuilderInterface extends OptionsBuilderInterface {

    SolutionsOptionsInterface forDelete();

    SolutionsOptionsInterface forAuth();

}
