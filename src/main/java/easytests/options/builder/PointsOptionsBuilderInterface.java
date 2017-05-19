package easytests.options.builder;

import easytests.options.PointsOptionsInterface;

/**
 * @author fortyways
 */
public interface PointsOptionsBuilderInterface extends OptionsBuilderInterface {

    PointsOptionsInterface forAuth();

    PointsOptionsInterface forDelete();
}
