package easytests.integration.services;

import easytests.models.UserModelInterface;
import easytests.services.UsersService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final UserModelInterface userModel = Models.createUserModel(
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

        Assert.assertEquals(userModel, foundedUserModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final UserModelInterface userModel = this.usersService.find(id);

        Assert.assertEquals(null, userModel);
    }
}
