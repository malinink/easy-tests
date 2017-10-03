package easytests.core.services;

import easytests.core.models.PointModelInterface;
import easytests.core.models.SolutionModelInterface;
import easytests.core.options.SolutionsOptionsInterface;
import java.util.List;


/**
 * @author Loriens
 */
public interface SolutionsServiceInterface extends ServiceInterface {
    List<SolutionModelInterface> findAll();

    List<SolutionModelInterface> findAll(SolutionsOptionsInterface solutionsOptions);

    SolutionModelInterface find(Integer id);

    SolutionModelInterface find(Integer id, SolutionsOptionsInterface solutionsOptions);

    List<SolutionModelInterface> findByPoint(PointModelInterface point);

    List<SolutionModelInterface> findByPoint(PointModelInterface point,
                                             SolutionsOptionsInterface solutionsOptions);

    void save(SolutionModelInterface solutionModel);

    void save(SolutionModelInterface solutionModel,
                                     SolutionsOptionsInterface solutionsOptions);

    void save(List<SolutionModelInterface> solutionModels, SolutionsOptionsInterface solutionsOptions);

    void save(List<SolutionModelInterface> solutionModels);

    void delete(SolutionModelInterface solutionModel);

    void delete(List<SolutionModelInterface> solutionsModel);

    void delete(SolutionModelInterface solutionModel, SolutionsOptionsInterface solutionsOptions);

    void delete(List<SolutionModelInterface> solutionsModel, SolutionsOptionsInterface solutionsOptions);
}
