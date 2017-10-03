package easytests.core.models;

import easytests.core.entities.UserEntity;
import easytests.core.models.empty.ModelsListEmpty;
import java.util.List;
import lombok.*;

/**
 * @author malinink
 */
@Data
public class UserModel implements UserModelInterface {
    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private String email;

    private String password;

    private Boolean isAdmin;

    private Integer state;

    private List<SubjectModelInterface> subjects;

    public void map(UserEntity userEntity) {
        this.setId(userEntity.getId());
        this.setFirstName(userEntity.getFirstName());
        this.setLastName(userEntity.getLastName());
        this.setSurname(userEntity.getSurname());
        this.setEmail(userEntity.getEmail());
        this.setPassword(userEntity.getPassword());
        this.setIsAdmin(userEntity.getIsAdmin());
        this.setState(userEntity.getState());
        this.setSubjects(new ModelsListEmpty());
    }
}
