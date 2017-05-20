package easytests.services;

import easytests.entities.SolutionEntity;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;

import java.util.List;

/**
 * @author loriens
 */
public interface SolutionsServiceInterface extends ServiceInterface {
    List<SolutionModelInterface> findAll();

    SolutionModelInterface find(Integer id);

    List<SolutionModelInterface> findByPoint(PointModelInterface point);

    void save(SolutionModelInterface solutionModel);

    void save(List<SolutionModelInterface> solutionModels);

    void delete(SolutionModelInterface solutionModel);

    SolutionModelInterface map(SolutionEntity solutionEntity);

    SolutionEntity map(SolutionModelInterface solutionModel);

    List<SolutionModelInterface> map(List<SolutionEntity> solutionEntities);
}
