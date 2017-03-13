package easytests.services;

import easytests.entities.*;
import easytests.mappers.SubjectsMapper;
import easytests.models.SubjectModel;
import easytests.models.SubjectModelInterface;

import easytests.services.exceptions.DeleteUnidentifiedModelException;

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

    public List<SubjectModelInterface> findAll() {
        return this.map(this.subjectsMapper.findAll());
    }

    public SubjectModelInterface find(Integer id) {
        final SubjectEntity subjectEntity = this.subjectsMapper.find(id);
        if (subjectEntity == null) {
            return null;
        }
        return this.map(subjectEntity);
    }

    public void save(SubjectModelInterface subjectModel) {
        final SubjectEntity subjectEntity = this.map(subjectModel);
        if (subjectEntity.getId() == null) {
            this.subjectsMapper.insert(subjectEntity);
            return;
        }
        this.subjectsMapper.update(subjectEntity);
    }

    public void delete(SubjectModelInterface subjectModel) {
        final SubjectEntity subjectEntity = this.map(subjectModel);
        if (subjectEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.subjectsMapper.delete(subjectEntity);
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
        final List<SubjectModelInterface> resultSubjectList = new ArrayList(subjectsList.size());
        for (SubjectEntity subject: subjectsList) {
            resultSubjectList.add(this.map(subject));
        }
        return resultSubjectList;
    }
}
