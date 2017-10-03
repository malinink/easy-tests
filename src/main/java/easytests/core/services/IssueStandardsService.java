package easytests.core.services;

import easytests.core.entities.*;
import easytests.core.mappers.IssueStandardsMapper;
import easytests.core.models.IssueStandardModel;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.options.IssueStandardsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardsService implements IssueStandardsServiceInterface {

    @Autowired
    private IssueStandardsMapper issueStandardsMapper;

    @Autowired
    private IssueStandardTopicPrioritiesService topicPrioritiesService;

    @Autowired
    private IssueStandardQuestionTypeOptionsService questionTypeOptionsService;

    @Autowired
    private SubjectsService subjectsService;

    @Override
    public List<IssueStandardModelInterface> findAll() {
        return this.map(this.issueStandardsMapper.findAll());
    }

    @Override
    public List<IssueStandardModelInterface> findAll(IssueStandardsOptionsInterface issueStandardsOptions) {
        return this.withServices(issueStandardsOptions).withRelations(this.findAll());
    }

    @Override
    public IssueStandardModelInterface find(Integer id) {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.find(id);
        if (issueStandardEntity == null) {
            return null;
        }
        return this.map(issueStandardEntity);
    }

    @Override
    public IssueStandardModelInterface find(Integer id, IssueStandardsOptionsInterface issueStandardsOptions) {
        return this.withServices(issueStandardsOptions).withRelations(this.find(id));
    }

    @Override
    public IssueStandardModelInterface findBySubject(SubjectModelInterface subject) {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.findBySubjectId(subject.getId());
        if (issueStandardEntity == null) {
            return null;
        }
        return this.map(issueStandardEntity);
    }

    @Override
    public IssueStandardModelInterface findBySubject(SubjectModelInterface subjectModel,
                                                     IssueStandardsOptionsInterface issueStandardsOptions) {
        return this.withServices(issueStandardsOptions).withRelations(this.findBySubject(subjectModel));
    }

    @Override
    public void save(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = this.map(issueStandardModel);
        if (issueStandardEntity.getId() != null) {
            this.issueStandardsMapper.update(issueStandardEntity);
            return;
        }
        this.issueStandardsMapper.insert(issueStandardEntity);
        issueStandardModel.setId(issueStandardEntity.getId());
    }

    @Override
    public void save(List<IssueStandardModelInterface> issueStandardModels) {
        for (IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            this.save(issueStandardModel);
        }
    }

    @Override
    public void save(IssueStandardModelInterface issueStandardModel,
                     IssueStandardsOptionsInterface issueStandardsOptions) {

        this.withServices(issueStandardsOptions).saveWithRelations(issueStandardModel);
    }

    @Override
    public void save(List<IssueStandardModelInterface> issueStandardModels,
                     IssueStandardsOptionsInterface issueStandardsOptions) {

        for (IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            this.save(issueStandardModel, issueStandardsOptions);
        }

    }

    @Override
    public void delete(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = this.map(issueStandardModel);
        if (issueStandardEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.issueStandardsMapper.delete(issueStandardEntity);
    }

    @Override
    public void delete(List<IssueStandardModelInterface> issueStandardModels) {
        for (IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            this.delete(issueStandardModel);
        }
    }

    @Override
    public void delete(IssueStandardModelInterface issueStandardModel,
                       IssueStandardsOptionsInterface issueStandardsOptions) {

        this.withServices(issueStandardsOptions).deleteWithRelations(issueStandardModel);
    }

    @Override
    public void delete(List<IssueStandardModelInterface> issueStandardModels,
                       IssueStandardsOptionsInterface issueStandardsOptions) {

        for (IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            this.delete(issueStandardModel, issueStandardsOptions);
        }
    }

    private IssueStandardsOptionsInterface withServices(IssueStandardsOptionsInterface issueStandardsOptions) {
        issueStandardsOptions.setIssueStandardsService(this);
        issueStandardsOptions.setTopicPrioritiesService(this.topicPrioritiesService);
        issueStandardsOptions.setQuestionTypeOptionsService(this.questionTypeOptionsService);
        issueStandardsOptions.setSubjectsService(this.subjectsService);
        return issueStandardsOptions;
    }

    private IssueStandardModelInterface map(IssueStandardEntity issueStandardEntity) {
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.map(issueStandardEntity);
        return issueStandardModel;
    }

    private IssueStandardEntity map(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = new IssueStandardEntity();
        issueStandardEntity.map(issueStandardModel);
        return issueStandardEntity;
    }

    private List<IssueStandardModelInterface> map(List<IssueStandardEntity> issueStandardEntities) {
        final List<IssueStandardModelInterface> issueStandardModels = new ArrayList<>(issueStandardEntities.size());
        for (IssueStandardEntity issueStandardEntity: issueStandardEntities) {
            issueStandardModels.add(this.map(issueStandardEntity));
        }
        return issueStandardModels;
    }
}
