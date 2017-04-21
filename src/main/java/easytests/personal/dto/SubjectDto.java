package easytests.personal.dto;

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

    private Integer issueStandardId;

}
