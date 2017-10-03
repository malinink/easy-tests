package easytests.core.services;

import easytests.core.entities.QuestionTypeEntity;
import easytests.core.mappers.QuestionTypesMapper;
import easytests.core.models.QuestionTypeModel;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.options.QuestionTypesOptionsInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class QuestionTypesService implements QuestionTypesServiceInterface {
    @Autowired
    private QuestionTypesMapper questionTypesMapper;

    @Override
    public List<QuestionTypeModelInterface> findAll() {
        return this.map(this.questionTypesMapper.findAll());
    }

    @Override
    public List<QuestionTypeModelInterface> findAll(QuestionTypesOptionsInterface questionTypesOptions) {
        return this.withServices(questionTypesOptions).withRelations(this.findAll());
    }

    @Override
    public QuestionTypeModelInterface find(Integer id) {
        return this.map(this.questionTypesMapper.find(id));
    }

    @Override
    public QuestionTypeModelInterface find(Integer id, QuestionTypesOptionsInterface questionTypesOptions) {
        return this.withServices(questionTypesOptions).withRelations(this.find(id));
    }

    private QuestionTypesOptionsInterface withServices(QuestionTypesOptionsInterface questionTypesOptions) {
        return questionTypesOptions;
    }

    private QuestionTypeModelInterface map(QuestionTypeEntity questionTypeEntity) {
        if (questionTypeEntity == null) {
            return null;
        }
        final QuestionTypeModelInterface questionTypeModel = new QuestionTypeModel();
        questionTypeModel.map(questionTypeEntity);
        return questionTypeModel;
    }

    private QuestionTypeEntity map(QuestionTypeModelInterface questionTypeModel) {
        final QuestionTypeEntity questionTypeEntity = new QuestionTypeEntity();
        questionTypeEntity.map(questionTypeModel);
        return questionTypeEntity;
    }

    private List<QuestionTypeModelInterface> map(List<QuestionTypeEntity> questionTypesEntities) {
        final List<QuestionTypeModelInterface> resultQuestionTypesEntities =
                new ArrayList(questionTypesEntities.size());
        for (QuestionTypeEntity questionTypeEntity: questionTypesEntities) {
            resultQuestionTypesEntities.add(this.map(questionTypeEntity));
        }
        return resultQuestionTypesEntities;
    }
}
