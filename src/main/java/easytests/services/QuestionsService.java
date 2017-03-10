package easytests.services;

import easytests.entities.QuestionEntity;
import easytests.mappers.QuestionsMapper;
import easytests.models.QuestionModel;
import easytests.models.QuestionModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author firkhraag
 */
@Service
public class QuestionsService {

    @Autowired
    private QuestionsMapper questionsMapper;

    public List<QuestionModelInterface> findAll() {
        return this.map(this.questionsMapper.findAll());
    }

    public QuestionModelInterface find(Integer id) {
        final QuestionEntity questionEntity = this.questionsMapper.find(id);
        if (questionEntity == null) {
            return null;
        }
        return this.map(questionEntity);
    }

    public void save(QuestionModelInterface questionModel) {
        final QuestionEntity questionEntity = this.map(questionModel);
        if (questionEntity.getId() == null) {
            this.questionsMapper.insert(questionEntity);
            return;
        }
        this.questionsMapper.update(questionEntity);
    }

    public void delete(QuestionModelInterface questionModel) {
        final QuestionEntity questionEntity = this.map(questionModel);
        if (questionEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.questionsMapper.delete(questionEntity);
    }

    private QuestionModelInterface map(QuestionEntity questionEntity) {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(questionEntity);
        return questionModel;
    }

    private QuestionEntity map(QuestionModelInterface questionModel) {
        final QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.map(questionModel);
        return questionEntity;
    }

    private List<QuestionModelInterface> map(List<QuestionEntity> questionsList) {
        final List<QuestionModelInterface> resultQuestionList = new ArrayList(questionsList.size());
        for (QuestionEntity question: questionsList) {
            resultQuestionList.add(this.map(question));
        }
        return resultQuestionList;
    }
}
