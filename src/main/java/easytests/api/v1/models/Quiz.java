package easytests.api.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * @author miron97
 */
@Data
public class Quiz {
    private Integer id;

    private Identity issue;

    private String inviteCode;

    private Boolean codeExpired;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String startedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String finishedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Testee testee;
}
