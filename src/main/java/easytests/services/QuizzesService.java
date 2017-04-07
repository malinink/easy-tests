package easytests.services;

import easytests.entities.*;
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

    public List<QuizInterface> findAll() {
        return quizzesMapper.findAll();
    }

    public QuizInterface find(Integer id) {
        return quizzesMapper.find(id);
    }

    public List<QuizInterface> findByIssue(IssueInterface issue) {
        return quizzesMapper.findByIssue(issue);
    }

    @Override
    public List<QuizModelInterface> findByIssue(IssueModelInterface issueModel,
                                                QuizzesOptionsInterface quizzesOptions) {
        return null;
    }

    public void save(QuizInterface quiz) {

        if (quiz.getId() == null) {
            this.quizzesMapper.insert(quiz);
        } else {
            this.quizzesMapper.update(quiz);
        }
    }

    @Override
    public void save(List<QuizModelInterface> quizModels, QuizzesOptionsInterface quizzesOptions) {
        //
    }

    public void delete(QuizInterface quiz) {
        this.quizzesMapper.delete(quiz);
    }

    @Override
    public void delete(List<QuizModelInterface> quizModels, QuizzesOptionsInterface quizzesOptions) {
        //
    }

}
