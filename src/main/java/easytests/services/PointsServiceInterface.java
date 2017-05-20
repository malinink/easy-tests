package easytests.services;

import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.options.PointsOptionsInterface;

import java.util.List;

/**
 * @author fortyways
 * @author Loriens
 */
public interface PointsServiceInterface extends ServiceInterface {
    List<PointModelInterface> findAll();

    PointModelInterface find(Integer id);

    PointModelInterface find(Integer id, PointsOptionsInterface pointsOptions);

    void save(PointModelInterface pointModel);

    void save(PointModelInterface pointModel,
              PointsOptionsInterface pointsOptions);

    void save(List<PointModelInterface> pointModels);

    void save(List<PointModelInterface> pointModels, PointsOptionsInterface pointsOptions);

    List<PointModelInterface> findByQuiz(QuizModelInterface quizModel);

    List<PointModelInterface> findByQuiz(QuizModelInterface quizModel, PointsOptionsInterface pointsOptions);

    void delete(PointModelInterface pointModel);

    void delete(PointModelInterface pointModel, PointsOptionsInterface pointsOptions);

    void delete(List<PointModelInterface> pointModels);

    void delete(List<PointModelInterface> pointModels, PointsOptionsInterface pointsOptions);
}
