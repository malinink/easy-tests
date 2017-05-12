package easytests.services;

import easytests.models.PointModelInterface;

import java.util.List;

/**
 * @author loriens
 */
public interface PointsServiceInterface extends ServiceInterface {
    List<PointModelInterface> findAll();

    PointModelInterface find(Integer id);

    void delete(PointModelInterface pointModel);
}
