package easytests.core.options.builder;

import easytests.core.options.PointsOptionsInterface;

/**
 * @author fortyways
 */
public interface PointsOptionsBuilderInterface extends OptionsBuilderInterface {

    PointsOptionsInterface forAuth();

    PointsOptionsInterface forDelete();
}
