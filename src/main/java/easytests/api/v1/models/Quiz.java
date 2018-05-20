package easytests.api.v1.models;

import easytests.core.models.TesteeModelInterface;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * @author miron97
 */
@Data
public class Quiz {
    private Integer id;

    private String inviteCode;

    private Boolean codeExpired;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    private TesteeModelInterface testee;
}
