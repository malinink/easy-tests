package easytests.support.meanbean.models;

import easytests.core.models.PointModel;
import easytests.core.models.PointModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author SvetlanaTselikova
 */
public class PointModelFactory implements Factory<PointModelInterface> {

    @Override
    public PointModelInterface create() {
        return new PointModel();
    }
}
