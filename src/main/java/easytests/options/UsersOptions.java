package easytests.options;

import easytests.models.UserModelInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.UsersServiceInterface;
import java.util.List;
import lombok.Setter;


/**
 * @author malinink
 */
public class UsersOptions implements UsersOptionsInterface {
    @Setter
    private UsersServiceInterface usersService;

    @Setter
    private SubjectsServiceInterface subjectsService;

    private SubjectsOptionsInterface subjectsOptions;

    @Override
    public UsersOptionsInterface withSubjects(SubjectsOptionsInterface subjectOptions) {
        this.subjectsOptions = subjectOptions;
        return this;
    }

    @Override
    public UserModelInterface setRelations(UserModelInterface userModel) {
        return userModel;
    }

    public List<UserModelInterface> setRelations(List<UserModelInterface> usersModels) {
        return usersModels;
    }

    @Override
    public void saveWithRelations(UserModelInterface userModel) {
        this.usersService.save(userModel);
    }

    @Override
    public void deleteWithRelations(UserModelInterface userModel) {
        this.usersService.delete(userModel);
    }
}
