package easytests.services;

import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
import easytests.options.SubjectsOptionsInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface SubjectsServiceInterface extends ServiceInterface {
    List<SubjectModelInterface> findAll();

    SubjectModelInterface find(Integer id);

    SubjectModelInterface find(Integer id, SubjectsOptionsInterface subjectsOptions);

    List<SubjectModelInterface> findByUser(UserModelInterface userModel);

    List<SubjectModelInterface> findByUser(UserModelInterface userModel, SubjectsOptionsInterface subjectsOptions);

    void save(SubjectModelInterface subjectModel);

    void save(SubjectModelInterface subjectModel, SubjectsOptionsInterface subjectsOptions);

    void save(List<SubjectModelInterface> subjectsModels);

    void save(List<SubjectModelInterface> subjectsModels, SubjectsOptionsInterface subjectsOptions);

    void delete(SubjectModelInterface subjectModel);

    void delete(SubjectModelInterface subjectModel, SubjectsOptionsInterface subjectsOptions);

    void delete(List<SubjectModelInterface> subjectsModels);

    void delete(List<SubjectModelInterface> subjectsModels, SubjectsOptionsInterface subjectsOptions);
}
