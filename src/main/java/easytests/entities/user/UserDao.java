package easytests.entities.user;

import javax.transaction.Transactional;
import easytests.entities.User;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    public User findByLastName(String lastName);
}