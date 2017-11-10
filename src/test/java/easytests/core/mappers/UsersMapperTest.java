package easytests.core.mappers;

import easytests.core.entities.UserEntity;
import easytests.support.UsersSupport;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author malinink
 */
public class UsersMapperTest extends AbstractMapperTest {

    private UsersSupport usersSupport = new UsersSupport();

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<UserEntity> usersEntities = this.usersMapper.findAll();

        Assert.assertEquals((long) 3, (long) usersEntities.size());

        Integer index = 0;
        for (UserEntity userEntity: usersEntities) {
            final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(index);

            this.usersSupport.assertEquals(userFixtureEntity, userEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final UserEntity userEntity = this.usersMapper.find(1);
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(0);

        this.usersSupport.assertEquals(userFixtureEntity, userEntity);
    }

    @Test
    public void testFindByEmail() throws Exception {
        final UserEntity userEntity = this.usersMapper.findByEmail("email2@gmail.com");
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(1);

        this.usersSupport.assertEquals(userFixtureEntity, userEntity);
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final UserEntity userAdditionalEntity = this.usersSupport.getEntityAdditionalMock(0);

        this.usersMapper.insert(userAdditionalEntity);

        verify(userAdditionalEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final UserEntity userInsertedEntity = this.usersMapper.find(id.getValue());

        this.usersSupport.assertEqualsWithoutId(userAdditionalEntity, userInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final UserEntity userAdditionalEntity = this.usersSupport.getEntityAdditionalMock(1);
        final Integer id = userAdditionalEntity.getId();
        final UserEntity userEntity = this.usersMapper.find(id);

        Assert.assertNotNull(userEntity);
        this.usersSupport.assertNotEqualsWithoutId(userAdditionalEntity, userEntity);

        this.usersMapper.update(userAdditionalEntity);
        final UserEntity userUpdatedEntity = this.usersMapper.find(id);

        this.usersSupport.assertEquals(userAdditionalEntity, userUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = 1;
        UserEntity userEntity = this.usersMapper.find(id);

        Assert.assertNotNull(userEntity);

        this.usersMapper.delete(userEntity);
        userEntity = this.usersMapper.find(id);

        Assert.assertNull(userEntity);
    }

}
