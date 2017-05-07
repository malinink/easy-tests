package easytests.options.builder;

import easytests.options.SubjectsOptionsInterface;


/**
 * @author malinink
 */
public interface SubjectsOptionsBuilderInterface extends OptionsBuilderInterface {
    SubjectsOptionsInterface forDelete();

    SubjectsOptionsInterface forAuth();
}
