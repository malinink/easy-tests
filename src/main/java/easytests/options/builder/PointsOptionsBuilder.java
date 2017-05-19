package easytests.options.builder;

import easytests.options.PointsOptionsInterface;
import org.springframework.stereotype.Service;

/**
 * @author fortyways
 */
@Service
public class PointsOptionsBuilder implements PointsOptionsBuilderInterface {

    @Override
    public PointsOptionsInterface forAuth() {
        return null;
    }

    @Override
    public PointsOptionsInterface forDelete() {
        return null;
    }

}
