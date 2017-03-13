package easytests.options;

import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
import easytests.services.SubjectsServiceInterface;


/**
 * @author malinink
 */
public class UsersOptions implements UsersOptionsInterface {
    @Override
    public UsersOptionsInterface withSubjects(SubjectModelInterface subjectModel) {
        return this;
    }

    @Override
    public void setSubjectsService(SubjectsServiceInterface subjectsService) {
    }

    @Override
    public void findWithOptions(UserModelInterface userModel) {
    }

    @Override
    public void saveWithOptions(UserModelInterface userModel) {
    }

    @Override
    public void deleteWithOptions(UserModelInterface userModel) {
    }
}
