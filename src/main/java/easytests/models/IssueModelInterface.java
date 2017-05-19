package easytests.models;

import easytests.entities.IssueEntity;

import java.util.List;

/**
 * @author fortyways
 */

public interface IssueModelInterface extends ModelInterface {

    void setId(Integer id);

    String getName();

    void setName(String name);

    SubjectModelInterface getSubject();

    void setSubject(SubjectModelInterface subject);

    void map(IssueEntity issueEntity);

    List<QuizModelInterface> getQuizzes();

    void setQuizzes(List<QuizModelInterface> quizzes);
}
