package easytests.core.models;

import easytests.core.entities.TesteeEntity;
import easytests.core.models.empty.QuizModelEmpty;
import lombok.*;

/**
 * @author DoZor-80
 */
@Data
public class TesteeModel implements TesteeModelInterface {
    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private Integer groupNumber;

    private QuizModelInterface quiz;

    public void map(TesteeEntity testeeEntity) {
        this.setId(testeeEntity.getId());
        this.setFirstName(testeeEntity.getFirstName());
        this.setLastName(testeeEntity.getLastName());
        this.setSurname(testeeEntity.getSurname());
        this.setGroupNumber(testeeEntity.getGroupNumber());
        this.setQuiz(new QuizModelEmpty(testeeEntity.getQuizId()));
    }
}
