package easytests.entities;

import easytests.models.UserModelInterface;
import lombok.*;

/**
 * @author malinink
 */
@Data
public class UserEntity {
    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    public void map(UserModelInterface userModel) {
        this.setId(userModel.getId());
        this.setFirstName(userModel.getFirstName());
        this.setLastName(userModel.getLastName());
        this.setSurname(userModel.getSurname());
    }
}
