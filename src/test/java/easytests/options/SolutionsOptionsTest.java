package easytests.options;

import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.models.empty.PointModelEmpty;
import easytests.services.PointsServiceInterface;
import easytests.services.SolutionsServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author loriens
 */
public class SolutionsOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        solutionModel.setId(1);
        given(solutionModel.getPoint()).willReturn(new PointModelEmpty(1));

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);

        given(pointsService.find(solutionModel.getPoint().getId(), pointsOptions)).willReturn(pointModel);

        final SolutionModelInterface solutionModelWithRelations = solutionsOptions.withRelations(solutionModel);

        Assert.assertEquals(solutionModel, solutionModelWithRelations);

        verify(pointsService).find(solutionModel.getPoint().getId(), pointsOptions);

        verify(solutionModel).setPoint(pointModel);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setPointsService(pointsService);

        final SolutionModelInterface nullSolutionModel = null;
        final SolutionModelInterface solutionModelWithRelations = solutionsOptions.withRelations(nullSolutionModel);

        Assert.assertNull(solutionModelWithRelations);

    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setSolutionsService(solutionsService);
        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        solutionModel.setPoint(pointModel);

        final InOrder inOrder = Mockito.inOrder(solutionsService, pointsService);

        solutionsOptions.saveWithRelations(solutionModel);

        inOrder.verify(solutionsService).save(solutionModel);
        inOrder.verify(pointsService).save(solutionModel.getPoint(), pointsOptions);

    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setSolutionsService(solutionsService);
        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        solutionModel.setPoint(pointModel);

        final InOrder inOrder = Mockito.inOrder(pointsService, solutionsService);

        solutionsOptions.deleteWithRelations(solutionModel);

        inOrder.verify(pointsService).delete(solutionModel.getPoint(), pointsOptions);
        inOrder.verify(solutionsService).delete(solutionModel);

    }

    @Test
    public void testSaveDeleteWithPoint() {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setSolutionsService(solutionsService);
        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);

        final SolutionsOptionsInterface solutionsOptionsSpy = Mockito.spy(solutionsOptions);

        solutionsOptionsSpy.deleteWithRelations(solutionModel);
        verify(pointsOptions, times(1)).deleteWithRelations(solutionModel.getPoint(), pointsOptions);

/*        solutionsOptionsSpy.saveWithRelations(solutionModel);
        verify(pointsOptions, times(1)).saveWithRelations(solutionModel.getPoint(), pointsOptions);*/

    }
}
