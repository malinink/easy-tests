package easytests.options;

import easytests.models.PointModelInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.SolutionsServiceInterface;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;


/**
 * @author nikitalpopov
 */
@EqualsAndHashCode
public class PointsOptions implements PointsOptionsInterface {

    @Setter
    private PointsServiceInterface pointsService;

    @Setter
    private QuizzesServiceInterface quizzesService;

    @Setter
    private QuestionsServiceInterface questionsService;

    @Setter
    private SolutionsServiceInterface solutionsService;

    private QuizzesOptionsInterface quizzesOptions;

    private QuestionsOptionsInterface questionsOptions;

    private SolutionsOptionsInterface solutionsOptions;

    public PointsOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions) {

        this.quizzesOptions = quizzesOptions;

        return this;

    }

    public PointsOptionsInterface withQuestion(QuestionsOptionsInterface questionsOptions) {

        this.questionsOptions = questionsOptions;

        return this;

    }

    public PointsOptionsInterface withSolutions(SolutionsOptionsInterface solutionsOptions) {

        this.solutionsOptions = solutionsOptions;

        return this;

    }

    public PointModelInterface withRelations(PointModelInterface pointModel) {

        if (pointModel == null) {

            return pointModel;

        }

        if (this.quizzesOptions != null) {
            pointModel.setQuiz(this.quizzesService.find(pointModel.getQuiz().getId(), this.quizzesOptions));
        }

        if (this.questionsOptions != null) {
            pointModel.setQuestion(this.questionsService.find(pointModel.getQuestion().getId(),
                    this.questionsOptions));
        }

        if (this.solutionsOptions != null) {
            pointModel.setSolutions(this.solutionsService.findByPoint(pointModel, this.solutionsOptions));
        }

        return pointModel;
    }

    public List<PointModelInterface> withRelations(List<PointModelInterface> pointModels) {

        for (PointModelInterface pointModel: pointModels) {
            this.withRelations(pointModel);
        }

        return pointModels;

    }

    public void saveWithRelations(PointModelInterface pointModel) {

        if (this.quizzesOptions != null) {
            this.quizzesOptions.withPoint(this);
            this.quizzesOptions.saveWithRelations(pointModel.getQuiz());

        }

        this.pointsService.save(pointModel);

        if (this.questionsOptions != null) {
            this.questionsService.save(pointModel.getQuestion(), this.questionsOptions);
        }

        if (this.solutionsOptions != null) {
            this.solutionsService.save(pointModel.getSolutions(), this.solutionsOptions);
        }

    }

    public void deleteWithRelations(PointModelInterface pointModel) {

        if (solutionsOptions != null) {
            this.solutionsService.delete(pointModel.getSolutions(), this.solutionsOptions);
        }

        if (questionsOptions != null) {
            this.questionsService.delete(pointModel.getQuestion(), this.questionsOptions);
        }

        if (this.quizzesOptions != null) {
            this.quizzesOptions.withPoint(this);
            this.quizzesOptions.deleteWithRelations(pointModel.getQuiz());
        }

        this.pointsService.delete(pointModel);

    }
}
