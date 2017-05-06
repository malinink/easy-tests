package easytests.admin.dto;

import easytests.models.UserModelInterface;
import easytests.models.empty.ModelsListEmpty;
import javax.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author malinink
 */
@Data
public class UserModelDto {
    @Null
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String surname;

    @NotNull
    @NotEmpty
    @Email
    @Size(min = 6, max = 30)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String passwordRepeat;

    @NotNull
    private Boolean isAdmin = false;

    @NotNull
    @Min(3)
    @Max(4)
    private Integer state = 3;

    public void mapInto(UserModelInterface userModel) {
        userModel.setFirstName(this.getFirstName());
        userModel.setLastName(this.getLastName());
        userModel.setSurname(this.getSurname());
        userModel.setEmail(this.getEmail());
        if (!this.getPassword().equals("")) {
            userModel.setPassword(this.getPassword());
        }
        userModel.setIsAdmin(this.getIsAdmin());
        userModel.setState(this.getState());
        userModel.setSubjects(new ModelsListEmpty());
    }

    public void map(UserModelInterface userModel) {
        this.setFirstName(userModel.getFirstName());
        this.setLastName(userModel.getLastName());
        this.setSurname(userModel.getSurname());
        this.setEmail(userModel.getEmail());
        this.setIsAdmin(userModel.getIsAdmin());
        this.setState(userModel.getState());
    }
}
