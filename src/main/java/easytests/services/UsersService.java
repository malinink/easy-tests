package easytests.services;

import easytests.entities.UserEntity;
import easytests.mappers.UsersMapper;
import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import easytests.options.UsersOptions;
import easytests.options.UsersOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
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

    @Override
    public List<UserModelInterface> findAll() {
        return this.findAll(new UsersOptions());
    }

    @Override
    public List<UserModelInterface> findAll(UsersOptionsInterface usersOptions) {
        return this.map(this.usersMapper.findAll());
    }

    @Override
    public UserModelInterface find(Integer id) {
        return this.find(id, new UsersOptions());
    }

    @Override
    public UserModelInterface find(Integer id, UsersOptionsInterface usersOptions) {
        final UserEntity userEntity = this.usersMapper.find(id);
        if (userEntity == null) {
            return null;
        }
        return this.map(userEntity);
    }

    @Override
    public void save(UserModelInterface userModel) {
        this.save(userModel, new UsersOptions());
    }

    @Override
    public void save(UserModelInterface userModel, UsersOptionsInterface usersOptions) {
        final UserEntity userEntity = this.map(userModel);
        if (userEntity.getId() == null) {
            this.usersMapper.insert(userEntity);
            userModel.setId(userEntity.getId());
            return;
        }
        this.usersMapper.update(userEntity);
    }

    @Override
    public void delete(UserModelInterface userModel) {
        this.delete(userModel, new UsersOptions());
    }

    @Override
    public void delete(UserModelInterface userModel, UsersOptionsInterface usersOptions) {
        final UserEntity userEntity = this.map(userModel);
        if (userEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.usersMapper.delete(userEntity);
    }

    private UserModelInterface map(UserEntity userEntity) {
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
        final List<UserModelInterface> resultUserList = new ArrayList(usersList.size());
        for (UserEntity user: usersList) {
            resultUserList.add(this.map(user));
        }
        return resultUserList;
    }
}
