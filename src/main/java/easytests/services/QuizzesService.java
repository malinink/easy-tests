package easytests.services;

import easytests.entities.*;
import easytests.mappers.QuizzesMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author vkpankov
 */
@Service
public class QuizzesService {

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

    public void save(QuizInterface quiz) {

        if (quiz.getId() == null) {
            this.quizzesMapper.insert(quiz);
        } else {
            this.quizzesMapper.update(quiz);
        }

    }

    public void delete(QuizInterface quiz) {
        this.quizzesMapper.delete(quiz);
    }
}
