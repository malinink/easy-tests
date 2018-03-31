package easytests.core.services;

import easytests.core.entities.QuestionEntity;
import easytests.core.mappers.QuestionsMapper;
import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author firkhraag
 */
@Service
public class QuestionsService implements QuestionsServiceInterface {

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private QuestionTypesService questionTypesService;

    @Override
    public List<QuestionModelInterface> findAll() {
        return this.map(this.questionsMapper.findAll());
    }

    @Override
    public List<QuestionModelInterface> findAll(QuestionsOptionsInterface questionsOptions) {
        return this.withServices(questionsOptions).withRelations(this.findAll());
    }

    @Override

    public QuestionModelInterface find(Integer id) {
        return this.map(this.questionsMapper.find(id));
    }

    @Override
    public QuestionModelInterface find(Integer id, QuestionsOptionsInterface questionsOptions) {
        return this.withServices(questionsOptions).withRelations(this.find(id));
    }

    @Override
    public List<QuestionModelInterface> findByTopic(TopicModelInterface topicModel) {
        return this.map(this.questionsMapper.findByTopicId(topicModel.getId()));
    }

    @Override
    public List<QuestionModelInterface> findByTopic(
            TopicModelInterface topicModel,
            QuestionsOptionsInterface questionsOptions
    ) {

        return this.withServices(questionsOptions)
                .withRelations(this.map(this.questionsMapper.findByTopicId(topicModel.getId())));
    }

    @Override
    public void save(QuestionModelInterface questionModel) {
        final QuestionEntity questionEntity = this.map(questionModel);
        if (questionEntity.getId() == null) {
            this.questionsMapper.insert(questionEntity);
            questionModel.setId(questionEntity.getId());
            return;
        }
        this.questionsMapper.update(questionEntity);
    }

    @Override
    public void save(QuestionModelInterface questionModel, QuestionsOptionsInterface questionsOptions) {
        this.withServices(questionsOptions).saveWithRelations(questionModel);
    }

    @Override
    public void save(List<QuestionModelInterface> questionsModels) {
        for (QuestionModelInterface questionModel : questionsModels) {
            this.save(questionModel);
        }
    }

    @Override
    public void save(List<QuestionModelInterface> questionsModels, QuestionsOptionsInterface questionsOptions) {
        for (QuestionModelInterface questionModel : questionsModels) {
            this.save(questionModel, questionsOptions);
        }
    }

    @Override
    public void delete(QuestionModelInterface questionModel) {
        final QuestionEntity questionEntity = this.map(questionModel);
        if (questionEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.questionsMapper.delete(questionEntity);
    }

    @Override
    public void delete(QuestionModelInterface questionModel, QuestionsOptionsInterface questionsOptions) {
        this.withServices(questionsOptions).deleteWithRelations(questionModel);
    }

    @Override
    public void delete(List<QuestionModelInterface> questionsModels) {
        for (QuestionModelInterface questionModel : questionsModels) {
            this.delete(questionModel);
        }
    }

    @Override
    public void delete(List<QuestionModelInterface> questionsModels, QuestionsOptionsInterface questionsOptions) {
        for (QuestionModelInterface questionModel : questionsModels) {
            this.delete(questionModel, questionsOptions);
        }
    }

    private QuestionsOptionsInterface withServices(QuestionsOptionsInterface questionsOptions) {
        questionsOptions.setQuestionsService(this);
        questionsOptions.setAnswersService(this.answersService);
        questionsOptions.setTopicsService(this.topicsService);
        questionsOptions.setQuestionTypesService(this.questionTypesService);
        return questionsOptions;
    }

    private QuestionModelInterface map(QuestionEntity questionEntity) {
        if (questionEntity == null) {
            return null;
        }
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
        for (QuestionEntity question : questionsList) {
            resultQuestionList.add(this.map(question));
        }
        return resultQuestionList;
    }
}
