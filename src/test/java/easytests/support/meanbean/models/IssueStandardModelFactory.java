package easytests.support.meanbean.models;

import easytests.core.models.IssueStandardModel;
import easytests.core.models.IssueStandardModelInterface;
import org.meanbean.lang.Factory;

/**
 * @author Risa_Magpie
 */
public class IssueStandardModelFactory implements Factory<IssueStandardModelInterface> {

    @Override
    public IssueStandardModelInterface create() {
            return new IssueStandardModel();
        }
}
