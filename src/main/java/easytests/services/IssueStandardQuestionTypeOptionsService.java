package easytests.services;

import easytests.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.mappers.IssueStandardQuestionTypeOptionsMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModel;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.options.IssueStandardQuestionTypeOptionsOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardQuestionTypeOptionsService implements IssueStandardQuestionTypeOptionsServiceInterface {

    @Autowired
    private IssueStandardQuestionTypeOptionsMapper questionTypeOptionsMapper;

    @Autowired
    private QuestionTypesServiceInterface questionTypesService;

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Override
    public List<IssueStandardQuestionTypeOptionModelInterface> findAll() {
        return this.map(this.questionTypeOptionsMapper.findAll());
    }

    @Override
    public List<IssueStandardQuestionTypeOptionModelInterface> findAll(
            IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        return this.withServices(questionTypeOptionsOptions).withRelations(this.findAll());
    }

    @Override
    public IssueStandardQuestionTypeOptionModelInterface find(Integer id) {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.questionTypeOptionsMapper.find(id);
        if (questionTypeOptionEntity == null) {
            return null;
        }
        return this.map(questionTypeOptionEntity);
    }

    @Override
    public IssueStandardQuestionTypeOptionModelInterface find(
            Integer id, IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        return this.withServices(questionTypeOptionsOptions).withRelations(this.find(id));
    }

    @Override
    public List<IssueStandardQuestionTypeOptionModelInterface>
        findByIssueStandard(IssueStandardModelInterface issueStandard) {

        return this.map(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandard.getId()));
    }

    @Override
    public List<IssueStandardQuestionTypeOptionModelInterface>
        findByIssueStandard(IssueStandardModelInterface issueStandard,
                        IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        return this.withServices(questionTypeOptionsOptions).withRelations(this.findByIssueStandard(issueStandard));
    }

    @Override
    public void save(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.map(questionTypeOptionModel);
        if (questionTypeOptionEntity.getId() != null) {
            this.questionTypeOptionsMapper.update(questionTypeOptionEntity);
            return;
        }
        this.questionTypeOptionsMapper.insert(questionTypeOptionEntity);
        questionTypeOptionModel.setId(questionTypeOptionEntity.getId());
    }

    @Override
    public void save(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels) {
        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            this.save(questionTypeOptionModel);
        }
    }

    @Override
    public void save(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel,
                     IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        this.withServices(questionTypeOptionsOptions).saveWithRelations(questionTypeOptionModel);
    }

    @Override
    public void save(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels,
                     IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            this.save(questionTypeOptionModel, questionTypeOptionsOptions);
        }
    }

    @Override
    public void delete(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.map(questionTypeOptionModel);
        if (questionTypeOptionEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.questionTypeOptionsMapper.delete(questionTypeOptionEntity);
    }

    @Override
    public void delete(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels) {
        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            this.delete(questionTypeOptionModel);
        }
    }

    @Override
    public void delete(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel,
                       IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        this.withServices(questionTypeOptionsOptions).deleteWithRelations(questionTypeOptionModel);
    }

    @Override
    public void delete(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels,
                       IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            this.delete(questionTypeOptionModel, questionTypeOptionsOptions);
        }
    }

    private IssueStandardQuestionTypeOptionsOptionsInterface
        withServices(IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {

        questionTypeOptionsOptions.setQuestionTypeOptionsService(this);
        questionTypeOptionsOptions.setQuestionTypesService(this.questionTypesService);
        questionTypeOptionsOptions.setIssueStandardsService(this.issueStandardsService);
        return questionTypeOptionsOptions;
    }

    private IssueStandardQuestionTypeOptionModelInterface
        map(IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity) {

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.map(questionTypeOptionEntity);
        return questionTypeOptionModel;
    }

    private IssueStandardQuestionTypeOptionEntity
        map(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {

        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = new IssueStandardQuestionTypeOptionEntity();
        questionTypeOptionEntity.map(questionTypeOptionModel);
        return questionTypeOptionEntity;
    }

    private List<IssueStandardQuestionTypeOptionModelInterface>
        map(List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities) {

        final List<IssueStandardQuestionTypeOptionModelInterface>
                questionTypeOptionModels = new ArrayList<>(questionTypeOptionEntities.size());

        for (IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity: questionTypeOptionEntities) {
            questionTypeOptionModels.add(this.map(questionTypeOptionEntity));
        }
        return questionTypeOptionModels;
    }
}
