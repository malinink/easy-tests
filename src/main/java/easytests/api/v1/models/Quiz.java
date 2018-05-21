package easytests.api.v1.models;

import lombok.Data;


/**
 * @author miron97
 */
@Data
public class Quiz {
    private Integer id;

    private String inviteCode;

    private Boolean codeExpired;

    private String startedAt;

    private String finishedAt;

    private Testee testee;
}
