package easytests.models;

import easytests.entities.UserEntity;
import easytests.models.empty.ModelsListEmpty;
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
        this.setSubjects(new ModelsListEmpty());
    }
}
