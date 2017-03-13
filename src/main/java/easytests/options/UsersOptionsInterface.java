package easytests.options;

import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
import easytests.services.SubjectsServiceInterface;


/**
 * @author malinink
 */
public interface UsersOptionsInterface {
    void withSubjects(SubjectModelInterface subjectModel);

    void setSubjectsService(SubjectsServiceInterface subjectsService);

    void findWithOptions(UserModelInterface userModel);

    void saveWithOptions(UserModelInterface userModel);

    void deleteWithOptions(UserModelInterface userModel);
}
