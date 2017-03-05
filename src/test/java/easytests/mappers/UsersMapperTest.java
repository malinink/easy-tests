package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.UserEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    public void testFindAll() throws Exception {
        final List<UserEntity> usersEntities = this.usersMapper.findAll();
        Assert.assertEquals((long) 3, (long) usersEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final UserEntity user = this.usersMapper.find(1);
        Assert.assertEquals((Integer) 1, user.getId());
        Assert.assertEquals("FirstName1", user.getFirstName());
        Assert.assertEquals("LastName1", user.getLastName());
        Assert.assertEquals("Surname1", user.getSurname());
    }

    @Test
    public void testInsert() throws Exception {
        final Integer id = this.usersMapper.findAll().size() + 1;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";

        UserEntity userEntity = Mockito.mock(UserEntity.class);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);

        this.usersMapper.insert(userEntity);

        verify(userEntity, times(1)).setId(id);

        userEntity = this.usersMapper.find(id);
        Assert.assertEquals(id, userEntity.getId());
        Assert.assertEquals(firstName, userEntity.getFirstName());
        Assert.assertEquals(lastName, userEntity.getLastName());
        Assert.assertEquals(surname, userEntity.getSurname());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final String firstName = "NewFirstName";
        final String lastName = "NewLastName";
        final String surname = "NewSurname";

        UserEntity userEntity = this.usersMapper.find(id);
        Assert.assertNotNull(userEntity);
        Assert.assertEquals(id, userEntity.getId());
        Assert.assertNotEquals(firstName, userEntity.getFirstName());
        Assert.assertNotEquals(lastName, userEntity.getLastName());
        Assert.assertNotEquals(surname, userEntity.getSurname());

        userEntity = Mockito.mock(UserEntity.class);
        Mockito.when(userEntity.getId()).thenReturn(id);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);

        this.usersMapper.update(userEntity);

        userEntity = this.usersMapper.find(id);
        Assert.assertEquals(id, userEntity.getId());
        Assert.assertEquals(firstName, userEntity.getFirstName());
        Assert.assertEquals(lastName, userEntity.getLastName());
        Assert.assertEquals(surname, userEntity.getSurname());
    }

    @Test
    public void testDelete() throws Exception {
        UserEntity userEntity = this.usersMapper.find(1);
        Assert.assertNotNull(userEntity);

        this.usersMapper.delete(userEntity);
        userEntity = this.usersMapper.find(1);
        Assert.assertNull(userEntity);
    }
}
