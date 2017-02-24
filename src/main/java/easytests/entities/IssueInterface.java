package easytests.entities;



/**
 * @author malinink
 */
public interface IssueInterface extends IssueStandardInterface {

    IssueInterface setId(Integer id);

    String getName();

    IssueInterface setName(String name);

    Integer getAuthorId();

    IssueInterface setAuthorId(Integer authorId);

    //List<Quiz> getQuizzes();

    //IssueInterface setQuizzes(List<Quiz> quizzes);
}
