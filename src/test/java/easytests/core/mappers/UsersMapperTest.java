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
        final List<UserEntity> usersFoundedEntities = this.usersMapper.findAll();

        Assert.assertEquals(3, usersFoundedEntities.size());

        Integer index = 0;
        for (UserEntity userEntity: usersFoundedEntities) {
            final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(index);

            this.usersSupport.assertEquals(userFixtureEntity, userEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(0);

        final UserEntity userFoundedEntity = this.usersMapper.find(userFixtureEntity.getId());

        this.usersSupport.assertEquals(userFixtureEntity, userFoundedEntity);
    }

    @Test
    public void testFindByEmail() throws Exception {
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(0);

        final UserEntity userFoundedEntity = this.usersMapper.findByEmail(userFixtureEntity.getEmail());

        this.usersSupport.assertEquals(userFixtureEntity, userFoundedEntity);
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final UserEntity userUnidentifiedEntity = this.usersSupport.getEntityAdditionalMock(0);

        this.usersMapper.insert(userUnidentifiedEntity);

        verify(userUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final UserEntity userInsertedEntity = this.usersMapper.find(id.getValue());

        Assert.assertNotNull(userInsertedEntity);
        this.usersSupport.assertEqualsWithoutId(userUnidentifiedEntity, userInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final UserEntity userChangedEntity = this.usersSupport.getEntityAdditionalMock(1);
        final UserEntity userBeforeUpdateEntity = this.usersMapper.find(userChangedEntity.getId());

        Assert.assertNotNull(userBeforeUpdateEntity);
        this.usersSupport.assertNotEqualsWithoutId(userChangedEntity, userBeforeUpdateEntity);

        this.usersMapper.update(userChangedEntity);
        final UserEntity userFoundedEntity = this.usersMapper.find(userChangedEntity.getId());

        this.usersSupport.assertEquals(userChangedEntity, userFoundedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.usersSupport.getEntityFixtureMock(0).getId();
        final UserEntity userFoundedEntity = this.usersMapper.find(id);

        Assert.assertNotNull(userFoundedEntity);

        this.usersMapper.delete(userFoundedEntity);

        Assert.assertNull(this.usersMapper.find(id));
    }

}
