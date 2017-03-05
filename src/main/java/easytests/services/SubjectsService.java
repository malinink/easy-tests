package easytests.services;

import easytests.entities.*;
import easytests.mappers.SubjectsMapper;
import easytests.models.UserModelInterface;
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
        return subjectsMapper.findAll();
    }

    public SubjectInterface find(Integer id) {
        return subjectsMapper.find(id);
    }

    public List<SubjectInterface> findByUser(UserModelInterface userModel) {
        return subjectsMapper.findByUserId(userModel.getId());
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
}
