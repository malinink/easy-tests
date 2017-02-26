package easytests.entities;

import java.util.List;

/**
 * @author malinink
 */
public interface TopicInterface extends EntityInterface {

    public  Integer getId();

    public  String getName();

    public  List<QuestionInterface> getQuestions();

}
