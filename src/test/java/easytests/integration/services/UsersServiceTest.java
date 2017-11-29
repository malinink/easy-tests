package easytests.integration.services;

import easytests.core.models.UserModelInterface;
import easytests.core.services.UsersService;
import easytests.support.UsersSupport;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author malinink
 */
public class UsersServiceTest extends AbstractServiceTest {

    @Autowired
    private UsersService usersService;

    private UsersSupport usersSupport = new UsersSupport();

    @Test
    public void testFindPresentModel() throws Exception {
        final UserModelInterface userFixtureModel = this.usersSupport.getModelFixtureMock(0);

        final UserModelInterface foundedUserModel = this.usersService.find(userFixtureModel.getId());

        this.usersSupport.assertEquals(userFixtureModel, foundedUserModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final UserModelInterface userModel = this.usersService.find(10);

        Assert.assertNull(userModel);
    }
}
