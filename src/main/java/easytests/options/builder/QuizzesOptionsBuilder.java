package easytests.options.builder;

import easytests.options.QuizzesOptions;
import easytests.options.QuizzesOptionsInterface;

/**
 * @author somebody
 */
public class QuizzesOptionsBuilder implements QuizzesOptionsBuilderInterface {

    @Override
    public QuizzesOptionsInterface forDelete() {

        return new QuizzesOptions();

    }

    @Override
    public QuizzesOptionsInterface forAuth() {

        return new QuizzesOptions();

    }

}
