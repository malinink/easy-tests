package easytests.core.mappers;

import easytests.config.DatabaseConfig;
import easytests.core.entities.UserEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Transactional
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
        final UserEntity userEntity = this.usersMapper.find(1);
        Assert.assertEquals((Integer) 1, userEntity.getId());
        Assert.assertEquals("FirstName1", userEntity.getFirstName());
        Assert.assertEquals("LastName1", userEntity.getLastName());
        Assert.assertEquals("Surname1", userEntity.getSurname());
        Assert.assertEquals("email1@gmail.com", userEntity.getEmail());
        Assert.assertEquals("hash1", userEntity.getPassword());
        Assert.assertEquals(true, userEntity.getIsAdmin());
        Assert.assertEquals((Integer) 1, userEntity.getState());
    }

    @Test
    public void testFindByEmail() throws Exception {
        final UserEntity userEntity = this.usersMapper.findByEmail("email1@gmail.com");
        Assert.assertEquals((Integer) 1, userEntity.getId());
        Assert.assertEquals("FirstName1", userEntity.getFirstName());
        Assert.assertEquals("LastName1", userEntity.getLastName());
        Assert.assertEquals("Surname1", userEntity.getSurname());
        Assert.assertEquals("email1@gmail.com", userEntity.getEmail());
        Assert.assertEquals("hash1", userEntity.getPassword());
        Assert.assertEquals(true, userEntity.getIsAdmin());
        Assert.assertEquals((Integer) 1, userEntity.getState());
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";
        final String email = "email@gmail.com";
        final String password = "hash";
        final Boolean isAdmin = true;
        final Integer state = 1;

        final UserEntity userEntity = Mockito.mock(UserEntity.class);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);
        Mockito.when(userEntity.getEmail()).thenReturn(email);
        Mockito.when(userEntity.getPassword()).thenReturn(password);
        Mockito.when(userEntity.getIsAdmin()).thenReturn(isAdmin);
        Mockito.when(userEntity.getState()).thenReturn(state);

        this.usersMapper.insert(userEntity);

        verify(userEntity, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        final UserEntity userEntityInserted = this.usersMapper.find(id.getValue());
        Assert.assertEquals(id.getValue(), userEntityInserted.getId());
        Assert.assertEquals(firstName, userEntityInserted.getFirstName());
        Assert.assertEquals(lastName, userEntityInserted.getLastName());
        Assert.assertEquals(surname, userEntityInserted.getSurname());
        Assert.assertEquals(email, userEntityInserted.getEmail());
        Assert.assertEquals(password, userEntityInserted.getPassword());
        Assert.assertEquals(isAdmin, userEntityInserted.getIsAdmin());
        Assert.assertEquals(state, userEntityInserted.getState());

    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final String firstName = "NewFirstName";
        final String lastName = "NewLastName";
        final String surname = "NewSurname";
        final String email = "new.email@gmail.com";
        final String password = "new.hash";
        final Boolean isAdmin = false;
        final Integer state = 2;


        UserEntity userEntity = this.usersMapper.find(id);
        Assert.assertNotNull(userEntity);
        Assert.assertEquals(id, userEntity.getId());
        Assert.assertNotEquals(firstName, userEntity.getFirstName());
        Assert.assertNotEquals(lastName, userEntity.getLastName());
        Assert.assertNotEquals(surname, userEntity.getSurname());
        Assert.assertNotEquals(email, userEntity.getEmail());
        Assert.assertNotEquals(password, userEntity.getPassword());
        Assert.assertNotEquals(isAdmin, userEntity.getIsAdmin());
        Assert.assertNotEquals(state, userEntity.getState());

        userEntity = Mockito.mock(UserEntity.class);
        Mockito.when(userEntity.getId()).thenReturn(id);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);
        Mockito.when(userEntity.getEmail()).thenReturn(email);
        Mockito.when(userEntity.getPassword()).thenReturn(password);
        Mockito.when(userEntity.getIsAdmin()).thenReturn(isAdmin);
        Mockito.when(userEntity.getState()).thenReturn(state);

        this.usersMapper.update(userEntity);

        userEntity = this.usersMapper.find(id);
        Assert.assertEquals(id, userEntity.getId());
        Assert.assertEquals(firstName, userEntity.getFirstName());
        Assert.assertEquals(lastName, userEntity.getLastName());
        Assert.assertEquals(surname, userEntity.getSurname());
        Assert.assertEquals(email, userEntity.getEmail());
        Assert.assertEquals(password, userEntity.getPassword());
        Assert.assertEquals(isAdmin, userEntity.getIsAdmin());
        Assert.assertEquals(state, userEntity.getState());
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
