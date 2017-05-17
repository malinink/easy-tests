package easytests.options.builder;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nikitalpopov
 */
public class PointsOptionsBuilder implements PointsOptionsBuilderInterface {

    @Autowired
    private SolutionsOptionsBuilder solutionsOptionsBuilder;

    @Override
    public PointsOptionsInterface forDelete() {

        return new PointsOptions()
                .withSolutions(this.solutionsOptionsBuilder.forDelete());

    }

    @Override
    public PointsOptionsInterface forAuth() {

        return new PointsOptions()
                .withSolutions(this.solutionsOptionsBuilder.forAuth());

    }

}
