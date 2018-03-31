package easytests.core.models;

import easytests.core.entities.PointEntity;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.QuestionModelEmpty;
import easytests.core.models.empty.QuizModelEmpty;

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
