package easytests.core.services;

import easytests.core.entities.UserEntity;
import easytests.core.mappers.UsersMapper;
import easytests.core.models.UserModel;
import easytests.core.models.UserModelInterface;
import easytests.core.options.UsersOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class UsersService implements UsersServiceInterface {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private SubjectsService subjectsService;

    @Override
    public List<UserModelInterface> findAll() {
        return this.map(this.usersMapper.findAll());
    }

    @Override
    public List<UserModelInterface> findAll(UsersOptionsInterface usersOptions) {
        return this.withServices(usersOptions).withRelations(this.findAll());
    }

    @Override
    public UserModelInterface find(Integer id) {
        return this.map(this.usersMapper.find(id));
    }

    @Override
    public UserModelInterface find(Integer id, UsersOptionsInterface usersOptions) {
        return this.withServices(usersOptions).withRelations(this.find(id));
    }

    @Override
    public UserModelInterface findByEmail(String email) {
        return this.map(this.usersMapper.findByEmail(email));
    }

    @Override
    public UserModelInterface findByEmail(String email, UsersOptionsInterface usersOptions) {
        return this.withServices(usersOptions).withRelations(this.findByEmail(email));
    }

    @Override
    public void save(UserModelInterface userModel) {
        final UserEntity userEntity = this.map(userModel);
        if (userEntity.getId() == null) {
            this.usersMapper.insert(userEntity);
            userModel.setId(userEntity.getId());
            return;
        }
        this.usersMapper.update(userEntity);
    }

    @Override
    public void save(UserModelInterface userModel, UsersOptionsInterface usersOptions) {
        this.withServices(usersOptions).saveWithRelations(userModel);
    }

    @Override
    public void delete(UserModelInterface userModel) {
        final UserEntity userEntity = this.map(userModel);
        if (userEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.usersMapper.delete(userEntity);
    }

    @Override
    public void delete(UserModelInterface userModel, UsersOptionsInterface usersOptions) {
        this.withServices(usersOptions).deleteWithRelations(userModel);
    }

    private UsersOptionsInterface withServices(UsersOptionsInterface usersOptions) {
        usersOptions.setUsersService(this);
        usersOptions.setSubjectsService(this.subjectsService);
        return usersOptions;
    }

    private UserModelInterface map(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        final UserModelInterface userModel = new UserModel();
        userModel.map(userEntity);
        return userModel;
    }

    private UserEntity map(UserModelInterface userModel) {
        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);
        return userEntity;
    }

    private List<UserModelInterface> map(List<UserEntity> usersList) {
        final List<UserModelInterface> resultUsersList = new ArrayList<>(usersList.size());
        for (UserEntity user: usersList) {
            resultUsersList.add(this.map(user));
        }
        return resultUsersList;
    }
}
