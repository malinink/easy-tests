package easytests.api.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;


/**
 * @author malinink
 */
@Data
public class Object {
    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private String email;

    private Boolean isAdmin;

    private Integer state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Identity> subjects;
}
