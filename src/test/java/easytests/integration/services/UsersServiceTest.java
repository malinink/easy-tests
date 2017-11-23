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
        final Integer id = 1;
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                id,
                "FirstName1",
                "LastName1",
                "Surname1",
                "email1@gmail.com",
                "hash1",
                true,
                1
        );

        final UserModelInterface foundedUserModel = this.usersService.find(id);

        this.usersSupport.assertEquals(userModel, foundedUserModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final UserModelInterface userModel = this.usersService.find(id);

        Assert.assertEquals(null, userModel);
    }
}
