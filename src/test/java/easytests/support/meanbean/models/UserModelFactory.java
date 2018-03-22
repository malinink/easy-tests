package easytests.support.meanbean.models;

import easytests.core.models.UserModel;
import easytests.core.models.UserModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author Risa_Magpie
 */
public class UserModelFactory implements Factory<UserModelInterface> {

    @Override
    public UserModelInterface create() {
        return new UserModel();
    }
}
