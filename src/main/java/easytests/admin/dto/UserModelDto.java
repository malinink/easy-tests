package easytests.admin.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author malinink
 */
@Data
public class UserModelDto {
    private Integer id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    private String email;

    private String password;

    private String passwordRepeat;

    @NotNull
    @NotEmpty
    private Boolean isAdmin;

    @NotNull
    @NotEmpty
    private Integer state;

}
