package easytests.services;

import easytests.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.mappers.IssueStandardQuestionTypeOptionsMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModel;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardQuestionTypeOptionsService {

    @Autowired
    private IssueStandardQuestionTypeOptionsMapper questionTypeOptionsMapper;

    List<IssueStandardQuestionTypeOptionModelInterface> findAll() {
        return this.map(this.questionTypeOptionsMapper.findAll());
    }

    IssueStandardQuestionTypeOptionModelInterface find(Integer id) {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.questionTypeOptionsMapper.find(id);
        if (questionTypeOptionEntity == null) {
            return null;
        }
        return this.map(questionTypeOptionEntity);
    }

    List<IssueStandardQuestionTypeOptionModelInterface> findByIssueStandard(IssueStandardModelInterface issueStandard) {
        return this.map(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandard.getId()));
    }

    public void save(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.map(questionTypeOptionModel);
        if (questionTypeOptionEntity.getId() != null) {
            this.questionTypeOptionsMapper.update(questionTypeOptionEntity);
            return;
        }
        this.questionTypeOptionsMapper.insert(questionTypeOptionEntity);
        questionTypeOptionModel.setId(questionTypeOptionEntity.getId());
    }

    public void delete(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.map(questionTypeOptionModel);
        if (questionTypeOptionEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.questionTypeOptionsMapper.delete(questionTypeOptionEntity);
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
