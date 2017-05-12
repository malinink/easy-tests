package easytests.services;

import easytests.entities.SolutionEntity;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.options.SolutionsOptionsInterface;

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

    SolutionsOptionsInterface withServices(SolutionsOptionsInterface solutionOptions);
}
