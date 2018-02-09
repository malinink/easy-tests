package easytests.core.mappers;

import easytests.core.entities.TesteeEntity;
import easytests.support.TesteesSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author janchk
 */
public class TesteesMapperTest extends AbstractMapperTest {

    protected TesteesSupport testeesSupport = new TesteesSupport();

    @Autowired
    private TesteesMapper testeesMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<TesteeEntity> testeesFoundedEntities = this.testeesMapper.findAll();

        Assert.assertEquals(3, testeesFoundedEntities.size());

        Integer index = 0;
        for (TesteeEntity testeeEntity: testeesFoundedEntities){
            final TesteeEntity testeeFixtureEntity = this.testeesSupport.getEntityFixtureMock(index);

            this.testeesSupport.assertEquals(testeeFixtureEntity, testeeEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final TesteeEntity testeeFixtureEntity = this.testeesSupport.getEntityFixtureMock(0);

        final TesteeEntity testeeFoundedEntity = this.testeesMapper.find(1);

        this.testeesSupport.assertEquals(testeeFixtureEntity, testeeFoundedEntity);
    }

    @Test
    public void testFindByQuizId() throws Exception {
        final TesteeEntity testeeFixtureEntity = this.testeesSupport.getEntityFixtureMock(2);
        
        final TesteeEntity testeeFoundedEntity = this.testeesMapper.findByQuizId(3);

        this.testeesSupport.assertEquals(testeeFixtureEntity, testeeFoundedEntity);
    }

    @Test
    public void testInsert() throws Exception{
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final TesteeEntity testeeUnidentifiedEntity = this.testeesSupport.getEntityAdditionalMock(0);

        this.testeesMapper.insert(testeeUnidentifiedEntity);

        verify(testeeUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final TesteeEntity testeeInsertedEntity = this.testeesMapper.find(id.getValue());

        Assert.assertNotNull(testeeInsertedEntity);
        this.testeesSupport.assertEqualsWithoutId(testeeUnidentifiedEntity, testeeInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception{
        final TesteeEntity testeeChangedEntity = this.testeesSupport.getEntityAdditionalMock(1);
        final Integer id = testeeChangedEntity.getId();
        final TesteeEntity testeeBeforeUpdateEntity = this.testeesMapper.find(id);

        Assert.assertNotNull(testeeBeforeUpdateEntity);
        this.testeesSupport.assertNotEqualsWithoutId(testeeChangedEntity, testeeBeforeUpdateEntity);

        this.testeesMapper.update(testeeChangedEntity);
        final TesteeEntity testeeUpdatedEntity = this.testeesMapper.find(id);

        this.testeesSupport.assertEquals(testeeChangedEntity, testeeUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception{
        final Integer id = this.testeesSupport.getEntityFixtureMock(0).getId();
        TesteeEntity testeeFoundedEntity = this.testeesMapper.find(id);

        Assert.assertNotNull(testeeFoundedEntity);

        this.testeesMapper.delete(testeeFoundedEntity);

        Assert.assertNull(this.testeesMapper.find(id));
    }

}
