package easytests.core.mappers;

import easytests.core.entities.PointEntity;
import easytests.support.PointsSupport;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SvetlanaTselikova
 */
public class PointsMapperTest extends AbstractMapperTest {

    private PointsSupport pointsSupport = new PointsSupport();

    @Autowired
    private PointsMapper pointsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<PointEntity> pointsFoundedEntities = this.pointsMapper.findAll();

        Assert.assertEquals(3, pointsFoundedEntities.size());
        Integer index = 0;
        for (PointEntity pointEntity : pointsFoundedEntities) {
            final PointEntity pointFixtureEntity = this.pointsSupport.getEntityFixtureMock(index);
            this.pointsSupport.assertEquals(pointFixtureEntity, pointEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final PointEntity pointFixtureEntity = this.pointsSupport.getEntityFixtureMock(0);

        final PointEntity pointFoundedEntity = this.pointsMapper.find(1);

        this.pointsSupport.assertEquals(pointFixtureEntity, pointFoundedEntity);
    }

    @Test
    public void testFindByQuizId() throws Exception {
        final List<PointEntity> pointsFixtureEntities = new ArrayList<>();
        pointsFixtureEntities.add(this.pointsSupport.getEntityFixtureMock(1));
        pointsFixtureEntities.add(this.pointsSupport.getEntityFixtureMock(2));

        final List<PointEntity> pointsFoundedEntities = this.pointsMapper.findByQuizId(2);

        Assert.assertEquals(2, pointsFoundedEntities.size());
        Integer index = 0;
        for (PointEntity pointEntity : pointsFoundedEntities) {
            this.pointsSupport.assertEquals(pointsFixtureEntities.get(index), pointEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final PointEntity pointUnidentifiedEntity = this.pointsSupport.getEntityAdditionalMock(0);

        this.pointsMapper.insert(pointUnidentifiedEntity);

        verify(pointUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());
        final PointEntity pointInsertedEntity = this.pointsMapper.find(id.getValue());
        Assert.assertNotNull(pointInsertedEntity);
        this.pointsSupport.assertEqualsWithoutId(pointUnidentifiedEntity, pointInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final PointEntity pointChangedEntity = this.pointsSupport.getEntityAdditionalMock(1);
        final PointEntity pointBeforeUpdateEntity = this.pointsMapper.find(pointChangedEntity.getId());
        Assert.assertNotNull(pointBeforeUpdateEntity);
        this.pointsSupport.assertNotEqualsWithoutId(pointChangedEntity, pointBeforeUpdateEntity);

        this.pointsMapper.update(pointChangedEntity);

        final PointEntity pointUpdatedEntity = this.pointsMapper.find(pointChangedEntity.getId());
        this.pointsSupport.assertEquals(pointChangedEntity, pointUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.pointsSupport.getEntityFixtureMock(0).getId();
        final PointEntity pointFoundedEntity = this.pointsMapper.find(id);
        Assert.assertNotNull(pointFoundedEntity);

        this.pointsMapper.delete(pointFoundedEntity);

        Assert.assertNull(this.pointsMapper.find(id));
    }
}
