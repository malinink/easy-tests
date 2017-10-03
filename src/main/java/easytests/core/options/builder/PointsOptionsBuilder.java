package easytests.core.options.builder;

import easytests.core.options.PointsOptions;
import easytests.core.options.PointsOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fortyways
 */
@Service
public class PointsOptionsBuilder implements PointsOptionsBuilderInterface {

    @Autowired
    private QuizzesOptionsBuilder quizzesOptionsBuilder;

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
                .withQuiz(this.quizzesOptionsBuilder.forAuth());

    }

}
