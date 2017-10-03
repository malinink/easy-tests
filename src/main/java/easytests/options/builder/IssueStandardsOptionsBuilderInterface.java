package easytests.options.builder;

import easytests.options.IssueStandardsOptionsInterface;

/**
 * @author SingularityA
 */
public interface IssueStandardsOptionsBuilderInterface extends OptionsBuilderInterface {
    IssueStandardsOptionsInterface forDelete();

    IssueStandardsOptionsInterface forAuth();
}
