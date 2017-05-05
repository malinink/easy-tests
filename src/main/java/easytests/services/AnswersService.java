package easytests.services;

import easytests.entities.AnswerEntity;
import easytests.mappers.AnswersMapper;
import easytests.models.AnswerModel;
import easytests.models.AnswerModelInterface;
import easytests.models.QuestionModelInterface;
import easytests.options.AnswersOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author rezenbekk
 */
@Service
public class AnswersService implements AnswersServiceInterface {

    @Autowired
    private AnswersMapper answersMapper;

    @Autowired
    private QuestionsService questionsService;

    @Override
    public List<AnswerModelInterface> findAll() {
        return this.map(this.answersMapper.findAll());
    }

    @Override
    public List<AnswerModelInterface> findByQuestion(QuestionModelInterface questionModel) {
        return this.map(this.answersMapper.findByQuestionId(questionModel.getId()));
    }
    
    @Override
    public List<AnswerModelInterface> findByQuestion(
            QuestionModelInterface questionModel,
            AnswersOptionsInterface answersOptions
    ) {
        return answersOptions.withRelations(this.map(this.answersMapper.findByQuestionId(questionModel.getId())));
    }

    @Override
    public AnswerModelInterface find(Integer id) {
        final AnswerEntity answerEntity = this.answersMapper.find(id);
        if (answerEntity == null) {
            return null;
        }
        return this.map(answerEntity);
    }

    @Override
    public AnswerModelInterface find(Integer id, AnswersOptionsInterface answersOptions) {
        return this.withServices(answersOptions).withRelations(this.find(id));

    }

    @Override
    public void save(AnswerModelInterface answerModel) {
        final AnswerEntity answerEntity = this.map(answerModel);
        if (answerEntity.getId() == null) {
            this.answersMapper.insert(answerEntity);
            answerModel.setId(answerEntity.getId());
            return;
        }
        this.answersMapper.update(answerEntity);
    }
    
    @Override
    public void save(AnswerModelInterface answerModel, AnswersOptionsInterface answersOptions) {
        this.withServices(answersOptions).saveWithRelations(answerModel);
    }
    
    @Override
    public void save(List<AnswerModelInterface> answersModels) {
        for (AnswerModelInterface answerModel: answersModels) {
            this.save(answerModel);
        }
    }

    @Override
    public void save(List<AnswerModelInterface> answersModels, AnswersOptionsInterface answersOptions) {
        for (AnswerModelInterface answerModel: answersModels) {
            this.save(answerModel, answersOptions);
        }
    }

    @Override
    public void delete(AnswerModelInterface answerModel) {
        final AnswerEntity answerEntity = this.map(answerModel);
        if (answerEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.answersMapper.delete(answerEntity);
    }
    
    @Override
    public void delete(AnswerModelInterface answerModel, AnswersOptionsInterface answersOptions) {
        this.withServices(answersOptions).deleteWithRelations(answerModel);
    }
    
    @Override
    public void delete(List<AnswerModelInterface> answersModels) {
        for (AnswerModelInterface answerModel: answersModels) {
            this.save(answerModel);
        }
    }

    @Override
    public void delete(List<AnswerModelInterface> answersModels, AnswersOptionsInterface answersOptions) {
        for (AnswerModelInterface answerModel: answersModels) {
            this.save(answerModel, answersOptions);
        }
    }
    
    private AnswersOptionsInterface withServices(AnswersOptionsInterface answersOptions) {
        answersOptions.setAnswersService(this);
        answersOptions.setQuestionsService(this.questionsService);
        return answersOptions;
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
