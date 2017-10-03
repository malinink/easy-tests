package easytests.core.options;

import easytests.core.models.UserModelInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.UsersServiceInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface UsersOptionsInterface extends OptionsInterface {
    void setSubjectsService(SubjectsServiceInterface subjectsService);

    void setUsersService(UsersServiceInterface usersService);

    UsersOptionsInterface withSubjects(SubjectsOptionsInterface subjectOptions);

    UserModelInterface withRelations(UserModelInterface userModel);

    List<UserModelInterface> withRelations(List<UserModelInterface> usersModels);

    void saveWithRelations(UserModelInterface userModel);

    void deleteWithRelations(UserModelInterface userModel);
}
