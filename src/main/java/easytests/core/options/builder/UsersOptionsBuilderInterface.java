package easytests.core.options.builder;

import easytests.core.options.UsersOptionsInterface;


/**
 * @author malinink
 */
public interface UsersOptionsBuilderInterface extends OptionsBuilderInterface {
    UsersOptionsInterface forDelete();

    UsersOptionsInterface forAuth();
}
