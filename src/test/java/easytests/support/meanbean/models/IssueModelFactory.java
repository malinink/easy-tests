package easytests.support.meanbean.models;

import easytests.core.models.IssueModel;
import easytests.core.models.IssueModelInterface;
import org.meanbean.lang.Factory;

public class IssueModelFactory implements Factory<IssueModelInterface> {

    @Override
    public IssueModelInterface create() {
        return new IssueModel();
    }
}
