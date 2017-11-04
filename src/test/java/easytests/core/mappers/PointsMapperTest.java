package easytests.core.mappers;

import easytests.core.entities.*;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author nikitalpopov
 */
public class PointsMapperTest extends AbstractMapperTest {

    @Autowired
    private PointsMapper pointsMapper;

    @Test
    public void testFindAll() throws Exception {

        final List<PointEntity> testPointEntities = this.pointsMapper.findAll();

        Assert.assertNotNull(testPointEntities);
        Assert.assertEquals((long) 3, (long) testPointEntities.size());

    }

    @Test
    public void testFind() throws Exception {

        final PointEntity testPoint = this.pointsMapper.find(1);

        Assert.assertNotNull(testPoint);
        Assert.assertEquals((Integer) 1, testPoint.getId());
        Assert.assertEquals((Integer) 1, testPoint.getQuestionId());
        Assert.assertEquals((Integer) 1, testPoint.getQuizId());

    }

    @Test
    public void testQuizNotNull() throws Exception {

        final List<PointEntity> pointEntities = this.pointsMapper.findByQuizId(1);

        Assert.assertNotNull(pointEntities);
        Assert.assertEquals(1, pointEntities.size());

    }

    @Test
    public void testFindByQuizId() throws Exception {

        final List<PointEntity> pointEntities = this.pointsMapper.findByQuizId(1);

        Assert.assertEquals(1, pointEntities.size());
        Assert.assertEquals((Integer)1, pointEntities.get(0).getQuestionId());
        Assert.assertEquals((Integer)1, pointEntities.get(0).getQuizId());
    }

    @Test
    public void testInsert() throws Exception {

        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final Integer testQuestionId = 1;
        final Integer testQuizId = 1;

        final PointEntity testPoint = Mockito.mock(PointEntity.class);

        Mockito.when(testPoint.getQuestionId()).thenReturn(testQuestionId);
        Mockito.when(testPoint.getQuizId()).thenReturn(testQuizId);

        pointsMapper.insert(testPoint);

        verify(testPoint, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        final PointEntity readPoint = pointsMapper.find(id.getValue());
        Assert.assertNotNull(readPoint);
        Assert.assertEquals(id.getValue(), readPoint.getId());
        Assert.assertEquals(testQuestionId, readPoint.getQuestionId());
        Assert.assertEquals(testQuizId, readPoint.getQuizId());

    }

    @Test
    public void testUpdate() throws Exception {

        final Integer testId = 2;
        final Integer testQuestionId = 1;
        final Integer testQuizId = 1;

        PointEntity testPoint = this.pointsMapper.find(testId);

        Assert.assertNotEquals(testQuestionId, testPoint.getQuestionId());
        Assert.assertNotEquals(testQuizId, testPoint.getQuizId());

        testPoint = Mockito.mock(PointEntity.class);

        Mockito.when(testPoint.getId()).thenReturn(testId);
        Mockito.when(testPoint.getQuestionId()).thenReturn(testQuestionId);
        Mockito.when(testPoint.getQuizId()).thenReturn(testQuizId);

        this.pointsMapper.update(testPoint);

        final PointEntity checkUpdatePoint = pointsMapper.find(testId);

        Assert.assertEquals(testQuestionId, checkUpdatePoint.getQuestionId());
        Assert.assertEquals(testQuizId, checkUpdatePoint.getQuizId());

    }

    @Test
    public void testDelete() throws Exception {

        PointEntity testPoint = this.pointsMapper.find(1);
        Assert.assertNotNull(testPoint);

        this.pointsMapper.delete(testPoint);

        testPoint = this.pointsMapper.find(1);
        Assert.assertNull(testPoint);

    }

}
