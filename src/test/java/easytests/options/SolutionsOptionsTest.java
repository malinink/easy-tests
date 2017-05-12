package easytests.options;

import easytests.models.AnswerModelInterface;
import easytests.models.PointModel;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.SolutionsServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 * @author loriens
 */
public class SolutionsOptionsTest {
/*    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        solutionModel.setId(1);
        given(solutionModel.getAnswer()).willReturn(new AnswerModelEmpty(1));

        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);

        given(pointsService.find(solutionModel.getPoint().getId(), pointsOptions)).willReturn(pointModel);

        final SolutionModelInterface solutionModelWithRelations = solutionsOptions.withRelations(solutionModel);

        Assert.assertEquals(solutionModel, solutionModelWithRelations);

        verify(pointsService).find(solutionModel.getPoint().getId(), pointsOptions);

        verify(solutionModel).setPoint(pointModel);
    }*/

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setPointsService(pointsService);

        final SolutionModelInterface nullSolutionModel = null;
        final SolutionModelInterface solutionModelWithRelations = solutionsOptions.withRelations(nullSolutionModel);

        Assert.assertNull(solutionModelWithRelations);

    }

/*    @Test
    public void testSaveWithRelations() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);

        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);

        final PointModelInterface pointModel = new PointModel();
        solutionModel.setPoint(pointModel);

        final InOrder inOrder = Mockito.inOrder(solutionsService,pointsService);

        solutionsOptions.saveWithRelations(solutionModel);

        inOrder.verify(solutionsService).save(solutionModel);
        inOrder.verify(pointsService).save(solutionModel.getPoint(), pointsOptions);

    }*/
}
