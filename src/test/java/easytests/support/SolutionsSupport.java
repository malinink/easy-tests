package easytests.support;

import easytests.core.entities.SolutionEntity;
import easytests.core.models.SolutionModelInterface;
import easytests.core.models.empty.AnswerModelEmpty;
import easytests.core.models.empty.PointModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author SvetlanaTselikova
 */
public class SolutionsSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    10,
                    1
            },
            {
                    2,
                    20,
                    1
            },
            {
                    3,
                    11,
                    2
            },
            {
                    4,
                    21,
                    2
            },
            {
                    5,
                    12,
                    3
            },
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    2,
                    2
            },
            {
                    // for update entity with id = 1
                    1,
                    3,
                    3
            },
    };

    public SolutionEntity getEntityFixtureMock(Integer index) {
        return  this.getEntityMock(fixtures[index]);
    }

    public SolutionEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private SolutionEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2]
        );
    }

    private SolutionEntity getEntityMock(
            Integer id,
            Integer answerId,
            Integer pointId
    ) {
        final SolutionEntity solutionEntity = Mockito.mock(SolutionEntity.class);
        Mockito.when(solutionEntity.getId()).thenReturn(id);
        Mockito.when(solutionEntity.getAnswerId()).thenReturn(answerId);
        Mockito.when(solutionEntity.getPointId()).thenReturn(pointId);
        return solutionEntity;
    }

    public SolutionModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public SolutionModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private SolutionModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2]
        );
    }

    private SolutionModelInterface getModelMock(
            Integer id,
            Integer answer,
            Integer point
    ) {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);
        Mockito.when(solutionModel.getId()).thenReturn(id);
        Mockito.when(solutionModel.getAnswer()).thenReturn(new AnswerModelEmpty(answer));
        Mockito.when(solutionModel.getPoint()).thenReturn(new PointModelEmpty(point));
        return solutionModel;
    }

    public void assertEquals(SolutionEntity expected, SolutionEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(SolutionEntity expected, SolutionEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(SolutionEntity expected, SolutionEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getAnswerId(), actual.getAnswerId());
        Assert.assertEquals(expected.getPointId(), actual.getPointId());
    }

    public void assertNotEquals(SolutionEntity unexpected, SolutionEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(SolutionEntity unexpected, SolutionEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(SolutionEntity unexpected, SolutionEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getAnswerId(), actual.getAnswerId());
        Assert.assertNotEquals(unexpected.getPointId(), actual.getPointId());
    }

    public void assertEquals(SolutionModelInterface expected, SolutionModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getAnswer(), actual.getAnswer());
        Assert.assertEquals(expected.getPoint(), actual.getPoint());
    }

    public void assertEquals(SolutionModelInterface expected, SolutionEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getAnswer().getId(), actual.getAnswerId());
        Assert.assertEquals(expected.getPoint().getId(), actual.getPointId());
    }

    public void assertEquals(SolutionEntity expected, SolutionModelInterface actual) {
        assertEquals(actual, expected);
    }

}
