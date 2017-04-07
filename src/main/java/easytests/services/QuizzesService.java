package easytests.services;

import easytests.mappers.QuizzesMapper;

import easytests.models.IssueModelInterface;
import easytests.models.QuizModelInterface;
import easytests.options.QuizzesOptionsInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author vkpankov
 */
@Service
public class QuizzesService implements QuizzesServiceInterface {
    @Autowired
    private QuizzesMapper quizzesMapper;

    @Override
    public List<QuizModelInterface> findByIssue(IssueModelInterface issueModel,
                                                QuizzesOptionsInterface quizzesOptions) {
        return null;
    }

    @Override
    public void save(List<QuizModelInterface> quizModels, QuizzesOptionsInterface quizzesOptions) {
        //
    }

    @Override
    public void delete(List<QuizModelInterface> quizModels, QuizzesOptionsInterface quizzesOptions) {
        //
    }
}
