package easytests.services;

import easytests.entities.Answer;
import easytests.entities.AnswerInterface;
import easytests.mappers.AnswersMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author rezenbekk
 */
@Service
public class AnswersService {

    @Autowired
    private AnswersMapper answersMapper;

    public List<AnswerInterface> findAll() {
        return this.map(this.answersMapper.findAll());
    }

    public AnswerInterface find(Integer id) {
        return this.answersMapper.find(id);
    }

    public void save(AnswerInterface answer) {
        if (answer.getId() == null) {
            this.answersMapper.insert(answer);
            return;
        }
        this.answersMapper.update(answer);
    }

    public void delete(AnswerInterface answer) {
        this.answersMapper.delete(answer);
    }

    private List<AnswerInterface> map(List<Answer> answersList) {
        final List<AnswerInterface> resultAnswerList = new ArrayList(answersList.size());
        for (Answer answer: answersList) {
            resultAnswerList.add(answer);
        }
        return resultAnswerList;
    }

}
