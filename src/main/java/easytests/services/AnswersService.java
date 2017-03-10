package easytests.services;

import easytests.entities.AnswerEntity;
import easytests.mappers.AnswersMapper;
import easytests.models.AnswerModel;
import easytests.models.AnswerModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
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

    public List<AnswerModelInterface> findAll() {
        return this.map(this.answersMapper.findAll());
    }

    public AnswerModelInterface find(Integer id) {
        final AnswerEntity answerEntity = this.answersMapper.find(id);
        if (answerEntity == null) {
            return null;
        }
        return this.map(answerEntity);
    }

    public void save(AnswerModelInterface answerModel) {
        final AnswerEntity answerEntity = this.map(answerModel);
        if (answerEntity.getId() == null) {
            this.answersMapper.insert(answerEntity);
            return;
        }
        this.answersMapper.update(answerEntity);
    }

    public void delete(AnswerModelInterface answerModel) {
        final AnswerEntity answerEntity = this.map(answerModel);
        if (answerEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.answersMapper.delete(answerEntity);
    }

    private AnswerModelInterface map(AnswerEntity answerEntity) {
        final AnswerModelInterface answerModel = new AnswerModel();
        answerModel.map(answerEntity);
        return answerModel;
    }

    private AnswerEntity map(AnswerModelInterface answerModel) {
        final AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.map(answerModel);
        return answerEntity;
    }

    private List<AnswerModelInterface> map(List<AnswerEntity> answersList) {
        final List<AnswerModelInterface> resultAnswerList = new ArrayList(answersList.size());
        for (AnswerEntity answer: answersList) {
            resultAnswerList.add(this.map(answer));
        }
        return resultAnswerList;
    }

}
