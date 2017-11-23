package easytests.core.mappers;

import easytests.core.entities.SolutionEntity;
import easytests.support.SolutionsSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;


public class SolutionsMapperTest extends AbstractMapperTest {

    protected SolutionsSupport solutionsSupport = new SolutionsSupport();

    @Autowired
    private SolutionsMapper solutionsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<SolutionEntity> solutionsEntities = this.solutionsMapper.findAll();

        Assert.assertEquals(5, solutionsEntities.size());

        Integer index = 0;
        for (SolutionEntity solutionEntity: solutionsEntities) {
            final SolutionEntity solutionFixtureEntity = this.solutionsSupport.getEntityFixtureMock(index);

            this.solutionsSupport.assertEquals(solutionFixtureEntity, solutionEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final SolutionEntity solutionFixtureEntity = this.solutionsSupport.getEntityFixtureMock(0);
        final SolutionEntity solutionEntity = this.solutionsMapper.find(solutionFixtureEntity.getId());

        this.solutionsSupport.assertEquals(solutionFixtureEntity, solutionEntity);
    }

    @Test
    public void testFindByPointIdWithoutSolutions() throws Exception {
        final List<SolutionEntity> solutionsEntities = this.solutionsMapper.findByPointId(5);

        Assert.assertEquals(0, solutionsEntities.size());
    }

    @Test
    public void testFindByPointId() throws Exception {
        final List<SolutionEntity> solutionsFixturesEntity = new ArrayList<>();
        solutionsFixturesEntity.add(this.solutionsSupport.getEntityFixtureMock(0));
        solutionsFixturesEntity.add(this.solutionsSupport.getEntityFixtureMock(1));
        final List<SolutionEntity> solutionEntities = this.solutionsMapper.findByPointId(1);

        Assert.assertEquals(2, solutionEntities.size());
        this.solutionsSupport.assertEquals(solutionsFixturesEntity.get(0), solutionEntities.get(0));
        this.solutionsSupport.assertEquals(solutionsFixturesEntity.get(1), solutionEntities.get(1));
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final SolutionEntity solutionAdditionalEntity = this.solutionsSupport.getEntityAdditionalMock(0);

        this.solutionsMapper.insert(solutionAdditionalEntity);

        verify(solutionAdditionalEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final SolutionEntity solutionInsertedEntity = this.solutionsMapper.find(id.getValue());

        Assert.assertNotNull(solutionInsertedEntity);
        this.solutionsSupport.assertEqualsWithoutId(solutionAdditionalEntity, solutionInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final SolutionEntity solutionAdditionalEntity = this.solutionsSupport.getEntityAdditionalMock(1);
        final Integer id = solutionAdditionalEntity.getId();
        final SolutionEntity solutionEntity = this.solutionsMapper.find(id);

        Assert.assertNotNull(solutionEntity);
        this.solutionsSupport.assertNotEqualsWithoutId(solutionAdditionalEntity, solutionEntity);

        this.solutionsMapper.update(solutionAdditionalEntity);
        final SolutionEntity solutionUpdatedEntity = this.solutionsMapper.find(id);

        this.solutionsSupport.assertEquals(solutionAdditionalEntity, solutionUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.solutionsSupport.getEntityFixtureMock(0).getId();
        SolutionEntity solutionEntity = this.solutionsMapper.find(id);

        Assert.assertNotNull(solutionEntity);

        this.solutionsMapper.delete(solutionEntity);
        solutionEntity = this.solutionsMapper.find(id);

        Assert.assertNull(solutionEntity);
    }


}