package easytests.services;

import easytests.entities.PointEntity;
import easytests.models.PointModel;
import easytests.models.PointModelInterface;
import easytests.options.PointsOptionsInterface;

import java.util.List;

/**
 * @author loriens
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

    PointsOptionsInterface withServices(PointsOptionsInterface pointsOptions);
}
