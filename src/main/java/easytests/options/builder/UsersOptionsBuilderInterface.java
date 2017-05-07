package easytests.options.builder;

import easytests.options.UsersOptionsInterface;


/**
 * @author malinink
 */
public interface UsersOptionsBuilderInterface extends OptionsBuilderInterface {
    UsersOptionsInterface forDelete();

    UsersOptionsInterface forAuth();
}
