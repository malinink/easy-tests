package easytests.support.meanbean.models;

import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author malinink
 */
public class TopicModelFactory implements Factory<TopicModelInterface> {

    @Override
    public TopicModelInterface create()
    {
        return new TopicModel();
    }
}
