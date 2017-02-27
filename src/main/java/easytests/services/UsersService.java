package easytests.services;

import easytests.entities.User;
import easytests.entities.UserInterface;
import easytests.mappers.UsersMapper;
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

    public List<UserInterface> findAll() {
        return this.map(this.usersMapper.findAll());
    }

    public UserInterface find(Integer id) {
        return this.usersMapper.find(id);
    }

    public void save(UserInterface user) {
        if (user.getId() == null) {
            this.usersMapper.insert(user);
            return;
        }
        this.usersMapper.update(user);
    }

    public void delete(UserInterface user) {
        this.usersMapper.delete(user);
    }

    private List<UserInterface> map(List<User> usersList) {
        final List<UserInterface> resultUserList = new ArrayList(usersList.size());
        for (User user: usersList) {
            resultUserList.add(user);
        }
        return resultUserList;
    }

}
