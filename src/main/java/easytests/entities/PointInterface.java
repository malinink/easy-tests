package easytests.entities;

import java.util.List;

/**
 * @author malinink
 */
public interface PointInterface extends EntityInterface {
    PointInterface setId(Integer id);

    String getText();

    PointInterface setText(String text);

    String getType();

    PointInterface setType(String type);

    Quiz getQuiz();

    PointInterface setQuiz(Quiz quiz);

    List<Solution> getSolutions();

    PointInterface setSolutions(List<Solution> solutions);
}
