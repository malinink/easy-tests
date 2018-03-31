package easytests.core.entities;

import easytests.core.models.UserModelInterface;
import lombok.*;
import org.modelmapper.ModelMapper;

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
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(userModel, this);
    }
}
