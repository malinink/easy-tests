package easytests.core.services;

import easytests.core.entities.*;
import easytests.core.mappers.SubjectsMapper;
import easytests.core.models.SubjectModel;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.UserModelInterface;
import easytests.core.options.SubjectsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author vkpankov
 */
@Service
public class SubjectsService implements SubjectsServiceInterface {

    @Autowired
    private SubjectsMapper subjectsMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private IssuesService issuesService;

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Override
    public List<SubjectModelInterface> findAll() {
        return this.map(this.subjectsMapper.findAll());
    }

    @Override
    public List<SubjectModelInterface> findAll(SubjectsOptionsInterface subjectsOptions) {
        return this.withServices(subjectsOptions).withRelations(this.findAll());
    }

    @Override
    public SubjectModelInterface find(Integer id) {
        final SubjectEntity subjectEntity = this.subjectsMapper.find(id);
        if (subjectEntity == null) {
            return null;
        }
        return this.map(subjectEntity);
    }

    @Override
    public SubjectModelInterface find(Integer id, SubjectsOptionsInterface subjectsOptions) {
        return this.withServices(subjectsOptions).withRelations(this.find(id));
    }

    @Override
    public List<SubjectModelInterface> findByUser(UserModelInterface userModel) {
        return this.map(this.subjectsMapper.findByUserId(userModel.getId()));
    }

    @Override
    public List<SubjectModelInterface> findByUser(
        UserModelInterface userModel,
        SubjectsOptionsInterface subjectsOptions
    ) {
        return this.withServices(subjectsOptions).withRelations(this.findByUser(userModel));
    }

    @Override
    public void save(SubjectModelInterface subjectModel) {
        final SubjectEntity subjectEntity = this.map(subjectModel);
        if (subjectEntity.getId() == null) {
            this.subjectsMapper.insert(subjectEntity);
            subjectModel.setId(subjectEntity.getId());
            return;
        }
        this.subjectsMapper.update(subjectEntity);
    }

    @Override
    public void save(SubjectModelInterface subjectModel, SubjectsOptionsInterface subjectsOptions) {
        this.withServices(subjectsOptions).saveWithRelations(subjectModel);
    }

    @Override
    public void save(List<SubjectModelInterface> subjectsModels) {
        for (SubjectModelInterface subjectModel: subjectsModels) {
            this.save(subjectModel);
        }
    }

    @Override
    public void save(List<SubjectModelInterface> subjectsModels, SubjectsOptionsInterface subjectsOptions) {
        for (SubjectModelInterface subjectModel: subjectsModels) {
            this.save(subjectModel, subjectsOptions);
        }
    }

    @Override
    public void delete(SubjectModelInterface subjectModel) {
        final SubjectEntity subjectEntity = this.map(subjectModel);
        if (subjectEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.subjectsMapper.delete(subjectEntity);
    }

    @Override
    public void delete(SubjectModelInterface subjectModel, SubjectsOptionsInterface subjectsOptions) {
        this.withServices(subjectsOptions).deleteWithRelations(subjectModel);
    }

    @Override
    public void delete(List<SubjectModelInterface> subjectsModels) {
        for (SubjectModelInterface subjectModel: subjectsModels) {
            this.delete(subjectModel);
        }
    }

    @Override
    public void delete(List<SubjectModelInterface> subjectsModels, SubjectsOptionsInterface subjectsOptions) {
        for (SubjectModelInterface subjectModel: subjectsModels) {
            this.delete(subjectModel, subjectsOptions);
        }
    }

    private SubjectEntity map(SubjectModelInterface subjectModel) {
        final SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.map(subjectModel);
        return subjectEntity;

    }

    private SubjectModel map(SubjectEntity subjectEntity) {
        final SubjectModel subjectModel = new SubjectModel();
        subjectModel.map(subjectEntity);
        return subjectModel;
    }

    private List<SubjectModelInterface> map(List<SubjectEntity> subjectsList) {
        final List<SubjectModelInterface> resultSubjectList = new ArrayList<>(subjectsList.size());
        for (SubjectEntity subject: subjectsList) {
            resultSubjectList.add(this.map(subject));
        }
        return resultSubjectList;
    }

    private SubjectsOptionsInterface withServices(SubjectsOptionsInterface subjectsOptions) {
        subjectsOptions.setIssuesService(this.issuesService);
        subjectsOptions.setSubjectsService(this);
        subjectsOptions.setUsersService(this.usersService);
        subjectsOptions.setIssueStandardsService(this.issueStandardsService);
        subjectsOptions.setTopicsService(this.topicsService);
        return subjectsOptions;
    }

}
