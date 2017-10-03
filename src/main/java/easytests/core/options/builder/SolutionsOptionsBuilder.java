package easytests.core.options.builder;

import easytests.core.options.SolutionsOptions;
import easytests.core.options.SolutionsOptionsInterface;
import org.springframework.stereotype.Service;


/**
 * @author nikitalpopov
 */
@Service
public class SolutionsOptionsBuilder implements SolutionsOptionsBuilderInterface {
    @Override
    public SolutionsOptionsInterface forDelete() {
        return new SolutionsOptions();
    }

    @Override
    public SolutionsOptionsInterface forAuth() {
        return new SolutionsOptions();
    }
}
