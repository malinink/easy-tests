package easytests.admin.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author malinink
 */
@Data
public class UserModelDto {
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

    private String password;

    private String passwordRepeat;

    @NotNull
    private Boolean isAdmin;

    @NotNull
    @Min(3)
    @Max(4)
    private Integer state = 3;
}
