package easytests.entities;

import easytests.models.TesteeModelInterface;
import lombok.*;

/**
 * @author DoZor-80
 */
@Data
public class TesteeEntity {

    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private Integer groupNumber;

    public void map(TesteeModelInterface testeeModel) {
        this.setId(testeeModel.getId());
        this.setFirstName(testeeModel.getFirstName());
        this.setLastName(testeeModel.getLastName());
        this.setSurname(testeeModel.getSurname());
        this.setGroupNumber(testeeModel.getGroupNumber());
    }
}
