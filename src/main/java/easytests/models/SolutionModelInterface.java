package easytests.models;

import easytests.entities.SolutionEntity;

/**
 * @author SingularityA
 */
public interface SolutionModelInterface extends ModelInterface {
    void setId(Integer id);

    void setSort(Integer sort);

    AnswerModelInterface getAnswer();

    void setAnswer(AnswerModelInterface answer);

    PointModelInterface getPoint();

    void setPoint(PointModelInterface point);

    void map(SolutionEntity solutionEntity);
}
