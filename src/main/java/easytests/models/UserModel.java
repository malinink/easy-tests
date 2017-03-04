package easytests.models;

import easytests.entities.UserEntity;
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

    private List<SubjectModelInterface> subjects;

    public void map(UserEntity userEntity) {
        this.setId(userEntity.getId());
        this.setFirstName(userEntity.getFirstName());
        this.setLastName(userEntity.getLastName());
        this.setSurname(userEntity.getSurname());
    }
}
