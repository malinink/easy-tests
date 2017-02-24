package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.User;
import easytests.entities.UserInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class UsersMapperTest {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testFind() throws Exception {
        final UserInterface user = this.usersMapper.find(1);

        Assert.assertEquals((long) 1, (long) user.getId());
        Assert.assertEquals("FirstName1", user.getFirstName());
        Assert.assertEquals("LastName1", user.getLastName());
        Assert.assertEquals("Surname1", user.getSurname());
    }

    @Test
    public void testDelete() throws Exception {
        User user = this.usersMapper.find(1);
        Assert.assertNotNull(user);
        this.usersMapper.delete(user);
        user = this.usersMapper.find(1);
        Assert.assertNull(user);
    }
}
