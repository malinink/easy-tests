package easytests.entities;

/**
 * @author malinink
 */
public interface SolutionInterface extends EntityInterface {

    SolutionInterface setId(Integer id);

    AnswerInterface getAnswer();

    SolutionInterface setAnswer(AnswerInterface answer);

    PointInterface getPoint();

    SolutionInterface setPoint(PointInterface point);
}
