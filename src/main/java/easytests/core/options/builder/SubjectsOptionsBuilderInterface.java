package easytests.core.options.builder;

import easytests.core.options.SubjectsOptionsInterface;


/**
 * @author malinink
 */
public interface SubjectsOptionsBuilderInterface extends OptionsBuilderInterface {
    SubjectsOptionsInterface forDelete();

    SubjectsOptionsInterface forAuth();
}
