package easytests.service;

import easytests.ConnectionFactory;
import easytests.dao.UserDAO;
import easytests.entities.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by vkpankov on 10.11.2016.
 */
public class UserService {
    public List<User> getAllUsers() throws Exception {
        SqlSession session = ConnectionFactory.getSession().openSession();
        UserDAO dao = session.getMapper(UserDAO.class);
        List<User> users = dao.getAllUsers();
        session.close();
        return users;
    }
    public void addUser(User user) throws Exception{
        SqlSession session = ConnectionFactory.getSession().openSession();
        UserDAO dao =session.getMapper(UserDAO.class);
        dao.addUser(user);
        session.commit();
        session.close();
    }
}
