package easytests.api.v1.mappers;

import easytests.api.v1.models.Issue;
import easytests.core.models.IssueModel;
import easytests.core.models.empty.ModelsListEmpty;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author Yarik2308
 */
@Service("IssuesMapperV1")
public class IssuesMapper extends ModelMapper {
    public IssuesMapper() {
        super();
        this.createTypeMap(Issue.class, IssueModel.class)
            .addMappings(
                mapper -> mapper.when(
                    context -> !(context.getSource() instanceof ModelsListEmpty)
                ).<Integer>map(issue -> issue.getSubject().getId(),
                    (issueModel, id) -> issueModel.getSubject().setId(id))
            );
    }
}
