package easytests.options;

import easytests.models.PointModelInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.SolutionsServiceInterface;
import groovy.transform.EqualsAndHashCode;
import lombok.Setter;

/**
 * @author loriens
 */
@EqualsAndHashCode
public class PointsOptions {
    @Setter
    private PointsServiceInterface pointsService;

    @Setter
    private QuizzesServiceInterface quizzesService;

    @Setter
    private SolutionsServiceInterface solutionsService;

    private QuizzesOptionsInterface quizzesOptions;

    private SolutionsOptionsInterface solutionsOptions;

    public PointsOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions) {
        this.quizzesOptions = quizzesOptions;
        return this;
    }

    public PointsOptionsInterface withSolution(SolutionsOptionsInterface solutionsOptions) {
        this.solutionsOptions = solutionsOptions;
        return this;
    }

    public PointModelInterface withRelations(PointModelInterface pointModel) {
        if (this.quizzesOptions != null) {
            pointModel.setPoint(this.pointsService.find(
                    pointModel.getQuiz().getId(), this.quizzesOptions));
        }

        return pointModel;
    }

    public void saveWithRelations(PointModelInterface pointModel) {
        return;
    }

    public void deleteWithRelations(PointModelInterface pointModel) {
        this.pointsService.delete(pointModel);
    }

}
