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


/**
 * @author Vlasovigor
 */
public class SolutionsMapperTest extends AbstractMapperTest {

    protected SolutionsSupport solutionsSupport = new SolutionsSupport();

    @Autowired
    private SolutionsMapper solutionsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<SolutionEntity> solutionsFoundedEntities = this.solutionsMapper.findAll();

        Assert.assertEquals(5, solutionsFoundedEntities.size());

        Integer index = 0;
        for (SolutionEntity solutionEntity: solutionsFoundedEntities) {
            final SolutionEntity solutionFixtureEntity = this.solutionsSupport.getEntityFixtureMock(index);

            this.solutionsSupport.assertEquals(solutionFixtureEntity, solutionEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final SolutionEntity solutionFixtureEntity = this.solutionsSupport.getEntityFixtureMock(0);

        final SolutionEntity solutionFoundedEntity = this.solutionsMapper.find(solutionFixtureEntity.getId());

        this.solutionsSupport.assertEquals(solutionFixtureEntity, solutionFoundedEntity);
    }

    @Test
    public void testFindByPointId() throws Exception {
        final List<SolutionEntity> solutionsFixtureEntities = new ArrayList<>();
        solutionsFixtureEntities.add(this.solutionsSupport.getEntityFixtureMock(0));
        solutionsFixtureEntities.add(this.solutionsSupport.getEntityFixtureMock(1));

        final List<SolutionEntity> solutionFoundedEntities = this.solutionsMapper.findByPointId(1);

        Assert.assertEquals(2, solutionFoundedEntities.size());

        Integer index = 0;
        for (SolutionEntity solutionEntity: solutionFoundedEntities) {
            this.solutionsSupport.assertEquals(solutionsFixtureEntities.get(index), solutionEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final SolutionEntity solutionUnidentifiedEntity = this.solutionsSupport.getEntityAdditionalMock(0);

        this.solutionsMapper.insert(solutionUnidentifiedEntity);

        verify(solutionUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final SolutionEntity solutionInsertedEntity = this.solutionsMapper.find(id.getValue());

        Assert.assertNotNull(solutionInsertedEntity);
        this.solutionsSupport.assertEqualsWithoutId(solutionUnidentifiedEntity, solutionInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final SolutionEntity solutionChangedEntity = this.solutionsSupport.getEntityAdditionalMock(1);
        final SolutionEntity solutionBeforeUpdateEntity = this.solutionsMapper.find(solutionChangedEntity.getId());

        Assert.assertNotNull(solutionBeforeUpdateEntity);
        this.solutionsSupport.assertNotEqualsWithoutId(solutionChangedEntity, solutionBeforeUpdateEntity);

        this.solutionsMapper.update(solutionChangedEntity);
        final SolutionEntity solutionUpdatedEntity = this.solutionsMapper.find(solutionChangedEntity.getId());

        this.solutionsSupport.assertEquals(solutionChangedEntity, solutionUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.solutionsSupport.getEntityFixtureMock(0).getId();
        final SolutionEntity solutionFoundedEntity = this.solutionsMapper.find(id);

        Assert.assertNotNull(solutionFoundedEntity);

        this.solutionsMapper.delete(solutionFoundedEntity);

        Assert.assertNull(this.solutionsMapper.find(id));
    }

}
