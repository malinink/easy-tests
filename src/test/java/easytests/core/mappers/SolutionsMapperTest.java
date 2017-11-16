package easytests.core.mappers;

import easytests.core.entities.*;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SingularityA
 */
public class SolutionsMapperTest extends AbstractMapperTest {

    @Autowired
    private SolutionsMapper solutionsMapper;

    @Test
    public void testFindAll() throws Exception {
        List<SolutionEntity> solutions = this.solutionsMapper.findAll();
        Assert.assertNotNull(solutions);
        Assert.assertEquals(5, solutions.size());
    }

    @Test
    public void testFind() throws Exception {
        SolutionEntity solution = this.solutionsMapper.find(1);
        Assert.assertNotNull(solution);
        Assert.assertEquals((Integer) 1, solution.getId());
        Assert.assertEquals((Integer) 10, solution.getAnswerId());
        Assert.assertEquals((Integer) 1, solution.getPointId());
    }

    @Test
    public void testFindByPointId() throws Exception {
        List<SolutionEntity> solutions = this.solutionsMapper.findByPointId(1);
        Assert.assertNotNull(solutions);
        Assert.assertEquals(2, solutions.size());
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final Integer answerId = 13;
        final Integer pointId = 4;

        SolutionEntity solutionEntity = Mockito.mock(SolutionEntity.class);
        Mockito.when(solutionEntity.getAnswerId()).thenReturn(answerId);
        Mockito.when(solutionEntity.getPointId()).thenReturn(pointId);

        this.solutionsMapper.insert(solutionEntity);

        verify(solutionEntity, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        solutionEntity = this.solutionsMapper.find(id.getValue());
        Assert.assertNotNull(solutionEntity);
        Assert.assertEquals(id.getValue(), solutionEntity.getId());
        Assert.assertEquals(answerId, solutionEntity.getAnswerId());
        Assert.assertEquals(pointId, solutionEntity.getPointId());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final Integer answerId = 13;
        final Integer pointId = 4;

        SolutionEntity solutionEntity = this.solutionsMapper.find(id);
        Assert.assertNotNull(solutionEntity);
        Assert.assertEquals(id, solutionEntity.getId());
        Assert.assertNotEquals(answerId, solutionEntity.getAnswerId());
        Assert.assertNotEquals(pointId, solutionEntity.getPointId());

        solutionEntity = Mockito.mock(SolutionEntity.class);
        Mockito.when(solutionEntity.getId()).thenReturn(id);
        Mockito.when(solutionEntity.getAnswerId()).thenReturn(answerId);
        Mockito.when(solutionEntity.getPointId()).thenReturn(pointId);

        this.solutionsMapper.update(solutionEntity);

        solutionEntity = this.solutionsMapper.find(id);
        Assert.assertNotNull(solutionEntity);
        Assert.assertEquals(id, solutionEntity.getId());
        Assert.assertEquals(answerId, solutionEntity.getAnswerId());
        Assert.assertEquals(pointId, solutionEntity.getPointId());
    }

    @Test
    public void testDelete() throws Exception {
        SolutionEntity solution = this.solutionsMapper.find(1);
        Assert.assertNotNull(solution);

        this.solutionsMapper.delete(solution);
        solution = this.solutionsMapper.find(1);
        Assert.assertNull(solution);
    }
}
