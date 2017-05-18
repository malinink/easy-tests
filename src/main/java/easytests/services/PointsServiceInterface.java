package easytests.services;

import easytests.models.PointModelInterface;
import easytests.options.PointsOptionsInterface;

import java.util.List;

/**
 * @author Loriens
 */
public interface PointsServiceInterface extends ServiceInterface {
    List<PointModelInterface> findAll();

    List<PointModelInterface> findAll(PointsOptionsInterface pointsOptions);

    PointModelInterface find(Integer id);

    PointModelInterface find(Integer id, PointsOptionsInterface pointsOptions);

    void save(PointModelInterface pointModel);

    void save(PointModelInterface pointModel,
              PointsOptionsInterface pointsOptions);

    void delete(PointModelInterface pointModel);

    void delete(PointModelInterface pointModel, PointsOptionsInterface pointsOptions);
}
