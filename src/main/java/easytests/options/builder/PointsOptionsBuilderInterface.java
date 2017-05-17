package easytests.options.builder;

import easytests.options.PointsOptionsInterface;

/**
 * @author nikitalpopov
 */
public interface PointsOptionsBuilderInterface extends OptionsBuilderInterface {

    PointsOptionsInterface forDelete();

    PointsOptionsInterface forAuth();

}
