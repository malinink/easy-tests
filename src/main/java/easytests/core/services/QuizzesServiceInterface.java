package easytests.core.services;

import easytests.core.models.IssueModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.QuizzesOptionsInterface;

import java.util.List;

/**
 * @author fortyways
 */
public interface QuizzesServiceInterface extends ServiceInterface {
    List<QuizModelInterface> findAll();

    List<QuizModelInterface> findAll(QuizzesOptionsInterface quizzesOptions);

    QuizModelInterface find(Integer id);

    QuizModelInterface find(Integer id, QuizzesOptionsInterface quizzesOptions);

    List<QuizModelInterface> findByIssue(IssueModelInterface issueModel);

    List<QuizModelInterface> findByIssue(IssueModelInterface issueModel, QuizzesOptionsInterface quizzesOptions);

    void save(QuizModelInterface quizModel);

    void save(QuizModelInterface quizModel, QuizzesOptionsInterface quizzesOptions);

    void save(List<QuizModelInterface> quizModels);

    void save(List<QuizModelInterface> quizModels, QuizzesOptionsInterface quizzesOptions);

    void delete(QuizModelInterface quizModel);

    void delete(QuizModelInterface quizModel, QuizzesOptionsInterface quizzesOptions);

    void delete(List<QuizModelInterface> quizModels);

    void delete(List<QuizModelInterface> quizModels, QuizzesOptionsInterface quizzesOptions);
}
