package easytests.services;

import easytests.entities.UserEntity;
import easytests.mappers.UsersMapper;
import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    public List<UserModelInterface> findAll() {
        return this.map(this.usersMapper.findAll());
    }

    public UserModelInterface find(Integer id) {
        final UserEntity userEntity = this.usersMapper.find(id);
        if (userEntity == null) {
            return null;
        }
        return this.map(userEntity);
    }

    public void save(UserModelInterface userModel) {
        final UserEntity userEntity = this.map(userModel);
        if (userEntity.getId() == null) {
            this.usersMapper.insert(userEntity);
            return;
        }
        this.usersMapper.update(userEntity);
    }

    public void delete(UserModelInterface userModel) {
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
