package easytests.core.services;

import easytests.core.models.PointModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.PointsOptionsInterface;

import java.util.List;

/**
 * @author Loriens
 */
public interface PointsServiceInterface extends ServiceInterface {
    List<PointModelInterface> findAll();

    List<PointModelInterface> findAll(PointsOptionsInterface pointsOptions);

    PointModelInterface find(Integer id);

    PointModelInterface find(Integer id, PointsOptionsInterface pointsOptions);

    List<PointModelInterface> findByQuiz(QuizModelInterface quizModel);
  
    List<PointModelInterface> findByQuiz(QuizModelInterface quizModel, PointsOptionsInterface pointsOptions);
  
    void save(PointModelInterface pointModel);

    void save(PointModelInterface pointModel, PointsOptionsInterface pointsOptions);

    void save(List<PointModelInterface> pointModels);

    void save(List<PointModelInterface> pointModels, PointsOptionsInterface pointsOptions);

    void delete(PointModelInterface pointModel);

    void delete(PointModelInterface pointModel, PointsOptionsInterface pointsOptions);

    void delete(List<PointModelInterface> pointModels);

    void delete(List<PointModelInterface> pointModels, PointsOptionsInterface pointsOptions);
}
