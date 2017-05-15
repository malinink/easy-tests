package easytests.services;

import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;

import java.util.List;

/**
 * @author nikitalpopov
 */
public interface PointsServiceInterface extends ServiceInterface {

    List<PointModelInterface> findAll();

    PointModelInterface find(Integer id);

    List<PointModelInterface> findByQuiz(QuizModelInterface quizModel);

    void save(PointModelInterface pointModel);

    void delete(PointModelInterface pointModel);

}
