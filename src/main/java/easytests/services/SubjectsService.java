package easytests.services;

import easytests.entities.*;
import easytests.mappers.SubjectsMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author vkpankov
 */
@Service
public class SubjectsService {

    @Autowired
    private SubjectsMapper subjectsMapper;

    public List<SubjectInterface> findAll() {
        return map(subjectsMapper.findAll());
    }

    public SubjectInterface find(Integer id) {
        return subjectsMapper.find(id);
    }

    public void save(SubjectInterface subject) {

        if (subject.getId() == null) {
            this.subjectsMapper.insert(subject);
        } else {
            this.subjectsMapper.update(subject);
        }

    }

    public void delete(SubjectInterface subject) {
        this.subjectsMapper.delete(subject);
    }

    private List<SubjectInterface> map(List<Subject> subjectList) {
        final List<SubjectInterface> resultSubjectList = new ArrayList(subjectList.size());
        for (Subject subject: subjectList) {
            resultSubjectList.add(subject);
        }
        return resultSubjectList;
    }

}
