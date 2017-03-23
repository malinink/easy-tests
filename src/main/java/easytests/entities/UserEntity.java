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

    private String email;

    private String password;

    private Boolean isAdmin;

    private Integer state;

    public void map(UserModelInterface userModel) {
        this.setId(userModel.getId());
        this.setFirstName(userModel.getFirstName());
        this.setLastName(userModel.getLastName());
        this.setSurname(userModel.getSurname());
    }
}
