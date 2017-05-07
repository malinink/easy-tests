package easytests.models;

import easytests.entities.PointEntity;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.QuestionModelEmpty;
import easytests.models.empty.QuizModelEmpty;
import java.util.List;

import lombok.Data;

/**
 * @author nikitalpopov
 */
@Data
public class PointModel implements PointModelInterface {

    private Integer id;

    private QuestionModelInterface question;

    private QuizModelInterface quiz;

    private List<SolutionModelInterface> solutions;

    public void map(PointEntity pointEntity) {

        this.setId(pointEntity.getId());
        this.setQuestion(new QuestionModelEmpty(pointEntity.getQuestionId()));
        this.setQuiz(new QuizModelEmpty(pointEntity.getQuizId()));
        this.setSolutions(new ModelsListEmpty());

    }

}
