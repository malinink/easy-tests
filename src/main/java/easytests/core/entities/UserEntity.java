package easytests.core.entities;

import easytests.core.models.UserModelInterface;
import lombok.*;

/**
 * @author malinink
 */
@Data
public class UserEntity implements EntityInterface {

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
        this.setEmail(userModel.getEmail());
        this.setPassword(userModel.getPassword());
        this.setIsAdmin(userModel.getIsAdmin());
        this.setState(userModel.getState());
    }

}
