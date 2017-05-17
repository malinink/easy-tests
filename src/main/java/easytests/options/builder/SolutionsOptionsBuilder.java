package easytests.options.builder;

import easytests.options.SolutionsOptionsInterface;

/**
 * @author somebody
 */
public class SolutionsOptionsBuilder implements SolutionsOptionsBuilderInterface {

    @Override
    public SolutionsOptionsInterface forDelete() {

        return new SolutionsOptions();

    }

    @Override
    public SolutionsOptionsInterface forAuth() {

        return new SolutionsOptons();

    }

}
