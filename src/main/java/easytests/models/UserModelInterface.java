package easytests.models;

import easytests.entities.UserEntity;
import java.util.List;

/**
 * @author malinink
 */
public interface UserModelInterface extends ModelInterface {
    void setId(Integer id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getSurname();

    void setSurname(String surname);

    List<SubjectModelInterface> getSubjects();

    void setSubjects(List<SubjectModelInterface> subjects);

    void map(UserEntity userEntity);
}
