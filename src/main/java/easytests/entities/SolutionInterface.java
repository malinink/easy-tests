package easytests.entities;

/**
 * @author malinink
 * @deprecated cause of models
 */
@Deprecated
public interface SolutionInterface extends EntityInterface {

    SolutionInterface setId(Integer id);

    AnswerInterface getAnswer();

    SolutionInterface setAnswer(AnswerInterface answer);

    PointInterface getPoint();

    SolutionInterface setPoint(PointInterface point);
}
