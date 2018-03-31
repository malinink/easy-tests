package easytests.core.options;

import easytests.core.models.UserModelInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.UsersServiceInterface;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Setter;


/**
 * @author malinink
 */
@EqualsAndHashCode
public class UsersOptions implements UsersOptionsInterface {
    @Setter
    private UsersServiceInterface usersService;

    @Setter
    private SubjectsServiceInterface subjectsService;

    private SubjectsOptionsInterface subjectsOptions;

    @Override
    public UsersOptionsInterface withSubjects(SubjectsOptionsInterface subjectsOptions) {
        this.subjectsOptions = subjectsOptions;
        return this;
    }

    @Override
    public UserModelInterface withRelations(UserModelInterface userModel) {
        if (userModel == null) {
            return userModel;
        }
        if (this.subjectsOptions != null) {
            userModel.setSubjects(this.subjectsService.findByUser(userModel, this.subjectsOptions));
        }
        return userModel;
    }

    @Override
    public List<UserModelInterface> withRelations(List<UserModelInterface> usersModels) {
        for (UserModelInterface userModel : usersModels) {
            this.withRelations(userModel);
        }
        return usersModels;
    }

    @Override
    public void saveWithRelations(UserModelInterface userModel) {
        this.usersService.save(userModel);
        if (this.subjectsOptions != null) {
            this.subjectsService.save(userModel.getSubjects(), this.subjectsOptions);
        }
    }

    @Override
    public void deleteWithRelations(UserModelInterface userModel) {
        if (this.subjectsOptions != null) {
            this.subjectsService.delete(userModel.getSubjects(), this.subjectsOptions);
        }
        this.usersService.delete(userModel);
    }
}
