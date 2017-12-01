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
        final List<TesteeEntity> testeesEntities = this.testeesMapper.findAll();
        Assert.assertEquals(3, testeesEntities.size());

        Integer index = 0;
        for (TesteeEntity testeeEntity: testeesEntities){
            final TesteeEntity testeeFixtureEntity = this.testeesSupport.getEntityFixtureMock(index);
            this.testeesSupport.assertEquals(testeeFixtureEntity, testeeEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final TesteeEntity testee = this.testeesMapper.find(1);
        final TesteeEntity testeeFixtureEntity = this.testeesSupport.getEntityFixtureMock(0);

        this.testeesSupport.assertEquals(testeeFixtureEntity, testee);
    }

    @Test
    public void testFindByQuizId() throws Exception {
        final TesteeEntity testee = this.testeesMapper.findByQuizId(3);
        final List<TesteeEntity> testeeFixtureEntities = new ArrayList<>();
        for(Integer index = 0; index < 2; index++){
            testeeFixtureEntities.add(this.testeesSupport.getEntityFixtureMock(index));
        }
        for (TesteeEntity testeeFixtureEntity: testeeFixtureEntities){
            if (testeeFixtureEntity.getQuizId() == 3){
                this.testeesSupport.assertEquals(testeeFixtureEntity, testee);
            }
        }
    }

    @Test
    public void testInsert() throws Exception{
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final TesteeEntity testeeAdditionalEntity = this.testeesSupport.getEntityAdditionalMock(0);
        this.testeesMapper.insert(testeeAdditionalEntity);

        verify(testeeAdditionalEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final TesteeEntity testeeInsertedEntity = this.testeesMapper.find(id.getValue());

        Assert.assertNotNull(testeeInsertedEntity);
        this.testeesSupport.assertEqualsWithoutId(testeeAdditionalEntity, testeeInsertedEntity);

    }

    @Test
    public void testUpdate() throws Exception{
        final TesteeEntity testeeAdditionalEntity = this.testeesSupport.getEntityAdditionalMock(1);
        final Integer id = testeeAdditionalEntity.getId();
        final TesteeEntity testeeEntity = this.testeesMapper.find(id);

        Assert.assertNotNull(testeeEntity);
        this.testeesSupport.assertNotEqualsWithoutId(testeeAdditionalEntity, testeeEntity);

        this.testeesMapper.update(testeeAdditionalEntity);
        final TesteeEntity updatedTesteeEntity = this.testeesMapper.find(id);

        this.testeesSupport.assertEquals(testeeAdditionalEntity, updatedTesteeEntity);
    }

    @Test
    public void testDelete() throws Exception{
        final Integer id = this.testeesSupport.getEntityFixtureMock(0).getId();
        TesteeEntity testeeEntity = this.testeesMapper.find(id);

        Assert.assertNotNull(testeeEntity);

        this.testeesMapper.delete(testeeEntity);

        testeeEntity = this.testeesMapper.find(id);
        Assert.assertNull(testeeEntity);
    }

}
