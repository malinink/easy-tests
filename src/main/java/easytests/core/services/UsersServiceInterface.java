package easytests.core.services;

import easytests.core.models.UserModelInterface;
import easytests.core.options.UsersOptionsInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface UsersServiceInterface extends ServiceInterface {
    List<UserModelInterface> findAll();

    List<UserModelInterface> findAll(UsersOptionsInterface usersOptions);

    UserModelInterface find(Integer id);

    UserModelInterface find(Integer id, UsersOptionsInterface usersOptions);

    UserModelInterface findByEmail(String email);

    UserModelInterface findByEmail(String email, UsersOptionsInterface usersOptions);

    void save(UserModelInterface userModel);

    void save(UserModelInterface userModel, UsersOptionsInterface usersOptions);

    void delete(UserModelInterface userModel);

    void delete(UserModelInterface userModel, UsersOptionsInterface usersOptions);
}
