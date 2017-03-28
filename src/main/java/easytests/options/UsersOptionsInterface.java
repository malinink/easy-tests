package easytests.options;

import easytests.models.UserModelInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.UsersServiceInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface UsersOptionsInterface {
    void setSubjectsService(SubjectsServiceInterface subjectsService);

    void setUsersService(UsersServiceInterface usersService);

    UsersOptionsInterface withSubjects(SubjectsOptionsInterface subjectOptions);

    UserModelInterface withRelations(UserModelInterface userModel);

    List<UserModelInterface> withRelations(List<UserModelInterface> usersModels);

    void saveWithRelations(UserModelInterface userModel);

    void deleteWithRelations(UserModelInterface userModel);
}
