package easytests.core.options.builder;

import easytests.core.options.IssueStandardsOptionsInterface;

/**
 * @author SingularityA
 */
public interface IssueStandardsOptionsBuilderInterface extends OptionsBuilderInterface {
    IssueStandardsOptionsInterface forDelete();

    IssueStandardsOptionsInterface forAuth();
}
