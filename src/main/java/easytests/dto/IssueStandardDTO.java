package easytests.dto;

import javax.validation.constraints.Min;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardDTO {

    @Min(value = 1)
    private Integer id;

    @Min(value = 0)
    private Integer timeLimit;

    @Min(value = 1)
    private Integer questionsNumber;

    // в идеале здесь листы TopicPriorityDTO и QuestionTypeOptionDTO

    // а здесь - SubjectDTO
    @Min(value = 1)
    private Integer subjectId;
}
