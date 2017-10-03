package easytests.core.options.builder;

import easytests.core.options.IssuesOptionsInterface;

/**
 * @author fortyways
 */
public interface IssuesOptionsBuilderInterface extends OptionsBuilderInterface {

    IssuesOptionsInterface forAuth();

    IssuesOptionsInterface forDelete();

}
