package easytests.personal.dto;

import easytests.models.IssueStandardModelInterface;
import easytests.models.UserModelInterface;
import lombok.Data;

/**
 * @author vkpankov
 */
@Data
public class SubjectDto {

    private Integer id;

    private String name;

    private String description;

    private Integer userId;

}
