package easytests.core.models;

import easytests.core.entities.UserEntity;

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

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Boolean getIsAdmin();

    void setIsAdmin(Boolean isAdmin);

    Integer getState();

    void setState(Integer state);

    List<SubjectModelInterface> getSubjects();

    void setSubjects(List<SubjectModelInterface> subjects);

    void map(UserEntity userEntity);
}
