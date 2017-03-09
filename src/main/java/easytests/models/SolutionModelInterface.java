package easytests.models;

import easytests.entities.SolutionEntity;

/**
 * @author SingularityA
 */
public interface SolutionModelInterface extends ModelInterface {
    void setId(Integer id);

    AnswerModelInterface getAnswer();

    void setAnswer(AnswerModelInterface answer);

    PointModelInterface getPoint();

    void setPoint(PointModelInterface point);

    void map(SolutionEntity solutionEntity);
}
