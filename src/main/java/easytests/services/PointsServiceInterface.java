package easytests.services;

import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.options.PointsOptionsInterface;

import java.util.List;

/**
 * @author nikitalpopov
 */
public interface PointsServiceInterface extends ServiceInterface {

    List<PointModelInterface> findAll();

    PointModelInterface find(Integer id);

    List<PointModelInterface> findByQuiz(QuizModelInterface quizModel);

    void save(PointModelInterface pointModel);

    void save(PointModelInterface pointModel, PointsOptionsInterface pointsOptions);

    void save(List<PointModelInterface> pointsModels);

    void save(List<PointModelInterface> pointsModels, PointsOptionsInterface pointsOptions);

    void delete(PointModelInterface pointModel);

    void delete(PointModelInterface pointModel, PointsOptionsInterface pointsOptions);

    void delete(List<PointModelInterface> pointsModels);

    void delete(List<PointModelInterface> pointsModels, PointsOptionsInterface pointsOptionsInterface);

}
