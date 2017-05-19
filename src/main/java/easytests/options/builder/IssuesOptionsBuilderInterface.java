package easytests.options.builder;

import easytests.options.IssuesOptionsInterface;

/**
 * @author fortyways
 */
public interface IssuesOptionsBuilderInterface extends OptionsBuilderInterface {

    IssuesOptionsInterface forAuth();

    IssuesOptionsInterface forDelete();

}
