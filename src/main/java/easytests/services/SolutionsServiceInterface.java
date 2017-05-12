package easytests.services;

import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.options.SolutionsOptionsInterface;
import java.util.List;


/**
 * @author loriens
 */
public interface SolutionsServiceInterface extends ServiceInterface {
    List<SolutionModelInterface> findAll();

    List<SolutionModelInterface> findAll(SolutionsOptionsInterface solutionsOptions);

    SolutionModelInterface find(Integer id);

    SolutionModelInterface find(Integer id, SolutionsOptionsInterface solutionsOptions);

    List<SolutionModelInterface> findByPoint(PointModelInterface point);

    void save(SolutionModelInterface solutionModel);

    void save(SolutionModelInterface solutionModel,
                                     SolutionsOptionsInterface solutionsOptions);

    void save(List<SolutionModelInterface> solutionModels, SolutionsOptionsInterface solutionsOptions);

    void save(List<SolutionModelInterface> solutionModels);

    void delete(SolutionModelInterface solutionModel);

    void delete(SolutionModelInterface solutionModel, SolutionsOptionsInterface solutionsOptions);
}
