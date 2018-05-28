package easytests.support.meanbean.models;

import easytests.core.models.SubjectModel;
import easytests.core.models.SubjectModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author greenbarrow
 */
public class SubjectModelFactory implements Factory<SubjectModelInterface> {

    @Override
    public SubjectModelInterface create() {
        return new SubjectModel();
    }
}
