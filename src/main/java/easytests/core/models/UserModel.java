package easytests.core.models;

import easytests.core.entities.UserEntity;
import easytests.core.models.empty.ModelsListEmpty;
import java.util.List;
import lombok.*;
import org.modelmapper.ModelMapper;

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
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(userEntity, this);
        this.setSubjects(new ModelsListEmpty());
    }
}
