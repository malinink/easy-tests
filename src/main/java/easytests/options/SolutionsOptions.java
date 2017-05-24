package easytests.options;

import easytests.models.SolutionModelInterface;
import easytests.services.AnswersServiceInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.SolutionsServiceInterface;
import groovy.transform.EqualsAndHashCode;
import java.util.List;
import lombok.Setter;

/**
 * @author Loriens
 */
@EqualsAndHashCode
public class SolutionsOptions implements SolutionsOptionsInterface {
    @Setter
    private SolutionsServiceInterface solutionsService;

    @Setter
    private PointsServiceInterface pointsService;

    @Setter
    private AnswersServiceInterface answersService;

    private PointsOptionsInterface pointsOptions;

    private AnswersOptionsInterface answersOptions;

    public SolutionsOptionsInterface withPoint(PointsOptionsInterface pointsOptions) {
        this.pointsOptions = pointsOptions;
        return this;
    }

    public SolutionsOptionsInterface withAnswer(AnswersOptionsInterface answersOptions) {
        this.answersOptions = answersOptions;
        return this;
    }

    @Override
    public SolutionModelInterface withRelations(SolutionModelInterface solutionModel) {
        if (solutionModel == null) {
            return solutionModel;
        }

        if (this.pointsOptions != null) {
            solutionModel.setPoint(this.pointsService.find(solutionModel.getPoint().getId(), this.pointsOptions));
        }

        if (this.answersOptions != null) {
            solutionModel.setAnswer(this.answersService.find(solutionModel.getAnswer().getId(), this.answersOptions));
        }

        return solutionModel;
    }

    @Override
    public List<SolutionModelInterface> withRelations(List<SolutionModelInterface> solutionModels) {

        for (SolutionModelInterface solutionModel: solutionModels) {
            this.withRelations(solutionModel);
        }
        return solutionModels;

    }

    public void saveWithRelations(SolutionModelInterface solutionModel) {
        this.solutionsService.save(solutionModel);

        if (this.pointsOptions != null) {
            this.pointsService.save(solutionModel.getPoint(), this.pointsOptions);
        }

        if (this.answersOptions != null) {
            this.answersService.save(solutionModel.getAnswer(), this.answersOptions);
        }
    }

    public void deleteWithRelations(SolutionModelInterface solutionModel) {
        if (this.answersOptions != null) {
            this.answersService.delete(solutionModel.getAnswer(), this.answersOptions);
            this.answersOptions.deleteWithRelations(solutionModel.getAnswer());
        }

        if (this.pointsOptions != null) {
            this.pointsService.delete(solutionModel.getPoint(), this.pointsOptions);
            this.pointsOptions.deleteWithRelations(solutionModel.getPoint());
        }

        this.solutionsService.delete(solutionModel);
    }

}
