package easytests.options;

import easytests.models.AnswerModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.models.empty.AnswerModelEmpty;
import easytests.models.empty.PointModelEmpty;
import easytests.services.AnswersServiceInterface;
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
 * @author Loriens
 */
public class SolutionsOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        solutionModel.setId(1);
        given(solutionModel.getPoint()).willReturn(new PointModelEmpty(1));
        given(solutionModel.getAnswer()).willReturn(new AnswerModelEmpty(1));

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final AnswersOptionsInterface answerOptions = Mockito.mock(AnswersOptionsInterface.class);

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);

        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.setAnswersService(answersService);
        solutionsOptions.withPoint(pointsOptions);
        solutionsOptions.withAnswer(answerOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);

        given(pointsService.find(solutionModel.getPoint().getId(), pointsOptions)).willReturn(pointModel);
        given(answersService.find(solutionModel.getAnswer().getId(), answerOptions)).willReturn(answerModel);

        final SolutionModelInterface solutionModelWithRelations = solutionsOptions.withRelations(solutionModel);

        Assert.assertEquals(solutionModel, solutionModelWithRelations);

        verify(pointsService).find(solutionModel.getPoint().getId(), pointsOptions);
        verify(answersService).find(solutionModel.getAnswer().getId(), answerOptions);

        verify(solutionModel).setPoint(pointModel);
        verify(solutionModel).setAnswer(answerModel);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);

        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.setAnswersService(answersService);

        final SolutionModelInterface nullSolutionModel = null;
        final SolutionModelInterface solutionModelWithRelations = solutionsOptions.withRelations(nullSolutionModel);

        Assert.assertNull(solutionModelWithRelations);

    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);

        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);

        solutionsOptions.setSolutionsService(solutionsService);
        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);
        solutionsOptions.setAnswersService(answersService);
        solutionsOptions.withAnswer(answersOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);
        solutionModel.setPoint(pointModel);
        solutionModel.setAnswer(answerModel);

        final InOrder inOrder = Mockito.inOrder(solutionsService, pointsService, answersService);

        solutionsOptions.saveWithRelations(solutionModel);

        inOrder.verify(solutionsService).save(solutionModel);
        inOrder.verify(pointsService).save(solutionModel.getPoint(), pointsOptions);
        inOrder.verify(answersService).save(solutionModel.getAnswer(), answersOptions);

    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);

        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);

        solutionsOptions.setSolutionsService(solutionsService);
        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);
        solutionsOptions.setAnswersService(answersService);
        solutionsOptions.withAnswer(answersOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);
        solutionModel.setPoint(pointModel);
        solutionModel.setAnswer(answerModel);

        final InOrder inOrder = Mockito.inOrder(pointsService, answersService, solutionsService);

        solutionsOptions.deleteWithRelations(solutionModel);

        inOrder.verify(pointsService).delete(solutionModel.getPoint(), pointsOptions);
        inOrder.verify(answersService).delete(solutionModel.getAnswer(), answersOptions);
        inOrder.verify(solutionsService).delete(solutionModel);

    }

    @Test
    public void testSaveDeleteWithPoint() {
        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);

        solutionsOptions.setSolutionsService(solutionsService);
        solutionsOptions.setPointsService(pointsService);
        solutionsOptions.withPoint(pointsOptions);
        solutionsOptions.setAnswersService(answersService);
        solutionsOptions.withAnswer(answersOptions);

        final SolutionsOptionsInterface solutionsOptionsSpy = Mockito.spy(solutionsOptions);

        solutionsOptionsSpy.deleteWithRelations(solutionModel);
        verify(pointsOptions, times(1)).deleteWithRelations(solutionModel.getPoint());

/*        solutionsOptionsSpy.saveWithRelations(solutionModel);
        verify(pointsOptions, times(1)).saveWithRelations(solutionModel.getPoint());*/

    }
}
