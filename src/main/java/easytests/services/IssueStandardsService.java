package easytests.services;

import easytests.entities.*;
import easytests.mappers.IssueStandardsMapper;
import easytests.models.IssueStandardModel;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.options.IssueStandardsOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
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

    public List<IssueStandardModelInterface> findAll() {
        return this.map(this.issueStandardsMapper.findAll());
    }

    public IssueStandardModelInterface find(Integer id) {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.find(id);
        if (issueStandardEntity == null) {
            return null;
        }
        return this.map(issueStandardEntity);
    }

    public IssueStandardModelInterface findBySubject(SubjectModelInterface subject) {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.findBySubjectId(subject.getId());
        if (issueStandardEntity == null) {
            return null;
        }
        return this.map(issueStandardEntity);
    }

    public IssueStandardModelInterface findBySubject(SubjectModelInterface subjectModel,
                                                     IssueStandardsOptionsInterface issueStandardsOptions) {
        return issueStandardsOptions.withRelations(
                this.map(this.issueStandardsMapper.findBySubjectId(subjectModel.getId())));
    }

    public void save(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = this.map(issueStandardModel);
        if (issueStandardEntity.getId() != null) {
            this.issueStandardsMapper.update(issueStandardEntity);
            return;
        }
        this.issueStandardsMapper.insert(issueStandardEntity);
        issueStandardModel.setId(issueStandardEntity.getId());
    }

    public void delete(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = this.map(issueStandardModel);
        if (issueStandardEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.issueStandardsMapper.delete(issueStandardEntity);
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
